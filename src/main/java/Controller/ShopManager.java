package Controller;

import Model.Client;
import Model.Queue;
import View.SimulationGUI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class ShopManager implements Runnable{

    /**
     * Somehow we have to check all the input data
     */

    public static final int SIMULATION_DELAY = 300;
    public static Thread mainThread;
    public static float averageWaitingTime = 0;
    public static float averageServiceTime = 0;
    public static int peakTime = -1;
    public static int peakNoOfClients = 0;
    public static int finishedClients = 0;

    public static int currentTime = 0;
    public static int simulationDuration = 45;
    public static int maxProcessingTime = 8;
    public static int minProcessingTime = 2;
    public static int maxArrivalTime = 30;
    public static int minArrivalTime = 2;
    public static int numberOfQueues = 3;
    public static int numberOfClients = 10;
    private static List<Client> generatedClients;

    private List<Client> concreteClients;
    private Scheduler scheduler;
    private PrintWriter out;
    private SimulationGUI viewImage;


    public ShopManager() {
        initComponents();
    }

    private void initComponents() {
        viewImage = new SimulationGUI();
        generatedClients = new ArrayList<>();
        //setConcreteClients();
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

    public static void generateClients() {
        Random r = new Random();
        for(int i = 0; i < numberOfClients; i++)
            generatedClients.add(new Client(i + 1,
                    r.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime,
                    r.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime));

        Collections.sort(generatedClients, new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                Integer arrival1 = o1.getArrivalTime();
                Integer arrival2 = o2.getArrivalTime();
                return arrival1.compareTo(arrival2);
            }
        });
    }

    @Override
    public void run() {
        scheduler = new Scheduler(numberOfQueues, Queue.MAX_CLIENTS_NUMBER);
        this.scheduler.changeStrategy(Scheduler.SelectionPolicy.SHORTEST_TIME);    //shortest queue strategy by default
        //start the threads of each queue by creating the scheduler
        try {
            out = new PrintWriter("output.txt");
            out.println("Log of Events:");
            out.println();
            while (currentTime < simulationDuration) {
                updateQueues(currentTime);
                viewImage.updateCurrentTime();
                printfCurrentStatus(out, currentTime);
                computePeak();
                currentTime++;
                Thread.sleep(SIMULATION_DELAY);
            }
            computeWaitingTimes();
            printResults(out);
            out.close();
            viewImage.setButtonToFinished();
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot Open The Specified File!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateQueues(int currentTime)  {
        int counter = 0;
        for (Client currentClient : generatedClients) {      //add clients to queues
            if (currentClient.getArrivalTime() != currentTime)
                break;
            //then add the client to the queue
            scheduler.addClientToOneQueue(currentClient);
            counter++;
        }

        for (int i = 0; i < counter; i++)                    //delete the clients from the waiting list
            this.generatedClients.remove(0);
    }

    private void computePeak() {
        int sum = sumOfQueues();
        if(peakNoOfClients < sum) {
            peakNoOfClients = sum;
            peakTime = currentTime;
        }
    }

    private void printResults(PrintWriter out) {
        if(peakTime == simulationDuration)
            peakTime--;
        if(finishedClients == 0) {
            out.println("Average Waiting Time: 0 s");
            out.println("Average Service Time: 0 s");
        }
        else {
            out.println("Average Waiting Time: " + averageWaitingTime / finishedClients + " s");
            out.println("Average Service Time: " + averageServiceTime / finishedClients + " s");
        }
        out.println("Finished Clients: " + finishedClients);
        out.println("Peak Time: " + peakTime);
        out.println("Peak Number of Clients: " + peakNoOfClients);
    }

    private void computeWaitingTimes() {
        for(Queue current: scheduler.getQueues()) {
            averageWaitingTime += current.getWaitingPeriod();
            averageServiceTime += current.getServicePeriod();
            finishedClients += current.getNumberFinishedClients();
        }
    }

    private int sumOfQueues() {
        int sum = 0;
        for(Queue current : scheduler.getQueues())
            sum += current.getSize();
        return sum;
    }

    private void printfCurrentStatus(PrintWriter out, int currentTime) {
        out.println("Time " + currentTime);
        out.print("Waiting Clients: ");
        String s = new String();
        for(Client current: generatedClients) {
            out.print(current.clientToString() + " ");
            s = s + current.clientToString() + " ";
        }
        if(numberOfQueues <= 5 && numberOfClients <= 30)
            viewImage.updateRemainingQueueText(s);
        out.print('\n');
        int i = 0;
        for(Queue currentQueue : scheduler.getQueues()) {
            i++;
            out.print("Queue ");
            out.printf("%3d", i);
            out.print(": ");
            if(numberOfQueues <= 5 && numberOfClients <= 30)
                viewImage.uploadGUIstatus(i - 1, currentQueue.getQueueStatus());
            out.print(currentQueue.getQueueStatus());
            out.print('\n');
        }
        out.print('\n');
    }

    public void setMainThread(Thread t) {
        this.mainThread = t;
    }

    private void printfSYSCurrentStatus(PrintWriter out, int currentTime) {
        System.out.println("Time " + currentTime);
        System.out.print("Waiting Clients: ");
        for(Client current: generatedClients)
            System.out.print(current.clientToString() + " ");
        System.out.print('\n');
        int i = 0;
        for(Queue currentQueue : scheduler.getQueues()) {
            i++;
            System.out.print("Queue ");
            System.out.printf("%3d", i);
            System.out.print(": ");
            currentQueue.getQueueStatus();
            viewImage.uploadGUIstatus(i - 1, currentQueue.getQueueStatus());
            System.out.print(currentQueue.getQueueStatus());
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
