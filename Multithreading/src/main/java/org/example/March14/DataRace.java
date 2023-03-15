package org.example.March14;

public class DataRace {
    public static void main(String[] args) {

        SharedClass sharedClass = new SharedClass();

        try {

            Thread thread1 = new Thread(() -> {

                for (int iterator = 0; iterator < Integer.MAX_VALUE; iterator++) {

                    sharedClass.increment();
                }
            });

            Thread thread2 = new Thread(() -> {

                for (int iterator = 0; iterator < Integer.MAX_VALUE; iterator++) {

                    sharedClass.checkForDataRace();
                }

            });

            thread1.start();

            thread2.start();
        }catch (Exception exception){

            exception.printStackTrace();
        }
    }

    public static class SharedClass {
        private int x = 0;
        private int y = 0;

        public void increment() {

            x++;

            y++;
        }

        public void checkForDataRace() {

            if (y > x) {

                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}
