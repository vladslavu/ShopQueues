package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    public enum SelectionPolicy{
        SHORTEST_QUEUE,
        SHORTEST_TIME
    }


    private List<Queue> queues;
    private int maxQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;
    private Thread[] queueThreads;

    public Scheduler(int maxQueues, int maxClientsPerQueue) {
        this.maxQueues = maxQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;

        this.queues = new ArrayList<>();
        this.queueThreads = new Thread[this.maxQueues];

        Queue current;              //make all the threads and start them
        for(int i = 0; i < maxQueues; i++) {
            current = new Queue();
            queues.add(current);
            queueThreads[i] = new Thread(current);
            queueThreads[i].start();
        }
    }

    public void changeStrategy(SelectionPolicy sel) {
        if(sel == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new StrategyQueue();
        if(sel == SelectionPolicy.SHORTEST_TIME)
            strategy = new StrategyTime();
    }

    public void addClientToOneQueue(Client newClient) {
        //we already have the strategy set, so we just want to call the addClient method.
        strategy.addClient(queues, newClient);
    }

    //getters
    public List<Queue> getQueues() {
        return this.queues;
    }

}
