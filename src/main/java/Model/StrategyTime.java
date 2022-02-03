package Model;

import java.util.List;

public class StrategyTime implements Strategy{

    private Queue getSmallestTimeQueue(List<Queue> queues) {
        int min = Integer.MAX_VALUE;
        int waitingTime;
        int index = -1;
        for(Queue current: queues) {
            if(current.getSize() > 0) {
                waitingTime = 0;
                for (Client c : current.getClients()) {
                    waitingTime += c.getCurrentWaitingPeriod();
                }
                if(waitingTime < min) {
                    min = waitingTime;
                    index = queues.indexOf(current);
                }
            }
            else
                return current;
        }
        return queues.get(index);
    }

    @Override
    public void addClient(List<Queue> queues, Client c) {
        Queue smallestTimeQueue = getSmallestTimeQueue(queues);
        smallestTimeQueue.addClient(c);
    }
}
