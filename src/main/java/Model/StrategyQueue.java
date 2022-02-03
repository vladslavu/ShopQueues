package Model;

import java.util.List;

public class StrategyQueue implements Strategy{

    private Queue getSmallestQueue(List<Queue> queues) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(Queue current: queues) {
            if(current.getSize() > 0) {
                if (current.getSize() < min) {
                    min = current.getSize();
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
        Queue smallestQueue = getSmallestQueue(queues);
        smallestQueue.addClient(c);
    }
}
