package Model;

import Controller.ShopManager;
import ForJUnitTest.ShopManagerForTesting;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{

    private int queueDuration = 0;
    private int queueWaitingPeriod = 0;
    private int queueServicePeriod = 0;
    private int queueFinishedClients = 0;

    public static final int MAX_CLIENTS_NUMBER = 40;
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public Queue() {
        waitingPeriod = new AtomicInteger(0);   //0 clients in the queue initially
        clients = new ArrayBlockingQueue<>(MAX_CLIENTS_NUMBER);
    }

    public void addClient(Client c) {
        try {
            this.clients.add(c);
            waitingPeriod.getAndIncrement();
            queueDuration += c.getProcessingTime();
            c.setFinishingTime(queueDuration - this.clients.peek().getProcessingTime() + this.clients.peek().getCurrentWaitingPeriod());
        }
        catch(Exception e) {
            System.out.println("Time " +  ShopManager.currentTime + ": Cannot add the Client with id" + c.getID());
        }
    }

    @Override
    public void run() {
        while(ShopManager.currentTime < ShopManager.simulationDuration) {
            if (this.clients.size() > 0) {
                try {
                    while (clients.peek().getCurrentWaitingPeriod() > 0) {
                        Thread.sleep(ShopManager.SIMULATION_DELAY);  //our simulation time step: 0.1 s
                        clients.peek().decrementWaitingPeriod();
                        queueDuration--;
                    }
                    if(ShopManager.currentTime == ShopManager.simulationDuration)
                        break;
                    Client c = clients.take();
                    queueWaitingPeriod += c.getFinishingTime();
                    queueServicePeriod += c.getProcessingTime();
                    queueFinishedClients++;
                    waitingPeriod.getAndDecrement();
                } catch(Exception e){
                    System.out.println("Cannot open the queue thread!");
                }
            }
            else
                queueDuration = 0;
        }
    }

    //getters
    public BlockingQueue<Client> getClients() {
        return this.clients;
    }

    public int getSize() {
        return this.waitingPeriod.get();
    }

    public int getWaitingPeriod() {
        return this.queueWaitingPeriod;
    }

    public int getNumberFinishedClients() {
        return this.queueFinishedClients;
    }

    public int getServicePeriod() {
        return this.queueServicePeriod;
    }

    public String getQueueStatus() {
        String status = new String();
        if (clients.size() == 0)
            return "Empty";
        for(Client currentClient : clients) {
            status = status + currentClient.clientToString() + " ";
        }
        return status;
    }
}
