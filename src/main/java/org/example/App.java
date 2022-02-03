package org.example;

import Controller.ShopManager;

public class App {
    public static void main(String[] args) {
        ShopManager shpMan = new ShopManager();
        Thread t = new Thread(shpMan);
        shpMan.setMainThread(t);
    }
}
