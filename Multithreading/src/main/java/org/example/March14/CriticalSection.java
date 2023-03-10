package org.example.March14;

public class CriticalSection {
    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);

        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        try{

        incrementingThread.start();

        decrementingThread.start();

        incrementingThread.join();

        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");

        }catch (Exception exception){

            exception.printStackTrace();
        }
    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {

            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {

            for (int iterator = 0; iterator < 1000000; iterator++) {

                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {

            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {

            for (int iterator = 0; iterator < 1000000; iterator++) {

                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        public void increment() {

            items++;
        }

        public void decrement() {

            items--;
        }

        public int getItems() {

            return items;
        }
    }
}
