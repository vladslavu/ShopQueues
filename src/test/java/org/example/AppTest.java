package org.example;

import ForJUnitTest.ShopManagerForTesting;
import org.junit.Assert;
import org.junit.Test;

public class AppTest 
{
    @Test
    public void TestMoreResults()
    {
        ShopManagerForTesting shpManTest = new ShopManagerForTesting(30, 3);
        Thread t = new Thread(shpManTest);
        try {
            t.start();
        }
        catch (Exception e){
            System.out.println("Cannot start the thread");
        }

        while(true) {
            if (!t.isAlive()) {
                Assert.assertEquals(17, ShopManagerForTesting.finishedClients); //finished no. of clients
                //Assert.assertEquals(3.8823528, ShopManagerForTesting.averageWaitingTime, 0.1); //average waiting time
                Assert.assertEquals(2.764706, ShopManagerForTesting.averageServiceTime, 0.1);  //average service time
                Assert.assertEquals(0, shpManTest.getConcreteClients().size()); //the remaining number of clients -> the clients waiting for entering a queue
                break;
            }
        }
    }

    @Test
    public void TestPeakTime()
    {
        ShopManagerForTesting shpManTest = new ShopManagerForTesting(30, 3);
        Thread t = new Thread(shpManTest);
        try {
            t.start();
        }
        catch (Exception e){
            System.out.println("Cannot start the thread");
        }

        while(true) {
            if (!t.isAlive()) {
                Assert.assertEquals(15, ShopManagerForTesting.peakTime);
                break;
            }
        }
    }

    @Test
    public void TestNoOfClientsAtPeakTime()
    {
        ShopManagerForTesting shpManTest = new ShopManagerForTesting(30, 3);
        Thread t = new Thread(shpManTest);
        try {
            t.start();
        }
        catch (Exception e){
            System.out.println("Cannot start the thread");
        }

        while(true) {
            if (!t.isAlive()) {
                Assert.assertEquals(8, ShopManagerForTesting.peakNoOfClients);
                break;
            }
        }
    }
}
