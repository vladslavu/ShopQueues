package Model;

public class Client {

    /**
     * This is our representation of a client.
     */
    private int id = 0;
    private int arrivalTime;
    private int processingTime;
    private int currentWaitingPeriod;
    private int finishingTime;
    public Client() {
        arrivalTime = 0;
        processingTime = 0;
    }

    public Client(int id, int arrivalTime, int processingTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.currentWaitingPeriod = this.processingTime;
    }

    //setters
    public void setFinishingTime(int timeWaitedOnQueue) {
        finishingTime = timeWaitedOnQueue;
    }

    //getters
    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public int getFinishingTime() {
        return this.finishingTime;
    }

    public int getCurrentWaitingPeriod() {
        return currentWaitingPeriod;
    }

    public int getID() {
        return this.id;
    }

    //other methods
    public void decrementWaitingPeriod() {
        this.currentWaitingPeriod--;
    }

    public String clientToString() {
        //I want the client representation: "(id arr proc)";
        String client = new String("(" + id + " " +arrivalTime + " " + currentWaitingPeriod + ")");
        return client;
    }
}
