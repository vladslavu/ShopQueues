package ForJUnitTest;

import Controller.ShopManager;
import Model.Client;
import Model.Queue;
import Controller.Scheduler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class ShopManagerForTesting implements Runnable{

    /**
     * Somehow we have to check all the input data
     */

    public static final int SIMULATION_DELAY = 100;
    public static float averageWaitingTime = 0;
    public static float averageServiceTime = 0;
    public static int peakTime = -1;
    public static int peakNoOfClients = 0;
    public static int finishedClients = 0;

    public static int currentTime = 0;
    public static int simulationDuration = 45;
    public static int numberOfQueues = 3;

    private List<Client> concreteClients;
    private Scheduler scheduler;


    public ShopManagerForTesting(int simulationDuration, int numberOfQueues) {
        ShopManagerForTesting.simulationDuration = simulationDuration;
        ShopManagerForTesting.numberOfQueues = numberOfQueues;
        initComponents();
    }

    private void initComponents() {
        setConcreteClients();
    }

    private void setConcreteClients() {
        concreteClients = new ArrayList<>();
        concreteClients.add(new Client(3, 3, 3));
        concreteClients.add(new Client(5, 4, 3));
        concreteClients.add(new Client(4, 7, 3));
        concreteClients.add(new Client(2, 9, 3));
        concreteClients.add(new Client(1, 9, 3));
        concreteClients.add(new Client(6, 9, 2));
        concreteClients.add(new Client(7, 9, 2));
        concreteClients.add(new Client(8, 9, 1));
        concreteClients.add(new Client(9, 10, 1));
        concreteClients.add(new Client(10, 12, 4));
        concreteClients.add(new Client(11, 13, 4));
        concreteClients.add(new Client(12, 13, 4));
        concreteClients.add(new Client(13, 14, 4));
        concreteClients.add(new Client(14, 14, 3));
        concreteClients.add(new Client(15, 15, 3));
        concreteClients.add(new Client(16, 15, 2));
        concreteClients.add(new Client(17, 15, 2));
    }

    @Override
    public void run() {
        scheduler = new Scheduler(numberOfQueues, Queue.MAX_CLIENTS_NUMBER);
        this.scheduler.changeStrategy(Scheduler.SelectionPolicy.SHORTEST_TIME);    //shortest queue strategy by default
        //start the threads of each queue by creating the scheduler
        try {
            while (currentTime < simulationDuration) {
                updateQueues(currentTime);
                computePeak();
                currentTime++;
                Thread.sleep(SIMULATION_DELAY);
            }
            computeWaitingTimes();

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void computeWaitingTimes() {
        for(Queue current: scheduler.getQueues()) {
            ShopManagerForTesting.averageWaitingTime += current.getWaitingPeriod();
            ShopManagerForTesting.averageServiceTime += current.getServicePeriod();
            ShopManagerForTesting.finishedClients += current.getNumberFinishedClients();
        }
        ShopManagerForTesting.averageWaitingTime /= ShopManagerForTesting.finishedClients;
        ShopManagerForTesting.averageServiceTime /= ShopManagerForTesting.finishedClients;
    }

    private void updateQueues(int currentTime)  {
        int counter = 0;
        for (Client currentClient : concreteClients) {      //add clients to queues
            if (currentClient.getArrivalTime() != currentTime)
                break;
            //then add the client to the queue
            scheduler.addClientToOneQueue(currentClient);
            counter++;
        }

        for (int i = 0; i < counter; i++)                    //delete the clients from the waiting list
            this.concreteClients.remove(0);
    }

    private void computePeak() {
        int sum = sumOfQueues();
        if(peakNoOfClients < sum) {
            peakNoOfClients = sum;
            peakTime = currentTime;
        }
    }

    private int sumOfQueues() {
        int sum = 0;
        for(Queue current : scheduler.getQueues())
            sum += current.getSize();
        return sum;
    }

    public List<Client> getConcreteClients() {
        return concreteClients;
    }

}
