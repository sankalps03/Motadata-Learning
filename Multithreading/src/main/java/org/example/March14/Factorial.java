package org.example.March14;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factorial {
    public static void main(String[] args) {

        List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> threads = new ArrayList<>();

        try{

        for (long inputNumber : inputNumbers) {

            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {

            thread.setDaemon(true);

            thread.start();
        }

        for (Thread thread : threads) {

            thread.join(2000);
        }

        for (int iterator = 0; iterator < inputNumbers.size(); iterator++) {

            FactorialThread factorialThread = threads.get(iterator);

            if (factorialThread.isFinished()) {

                System.out.println("Factorial of " + inputNumbers.get(iterator) + " is " + factorialThread.getResult());

            } else {

                System.out.println("The calculation for " + inputNumbers.get(iterator) + " is still in progress");
            }
        }
        }catch (Exception exception){

            exception.printStackTrace();
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {

            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {

            this.result = factorial(inputNumber);

            this.isFinished = true;
        }

        public BigInteger factorial(long n) {

            BigInteger tempResult = BigInteger.ONE;

            for (long iterator = n; iterator > 0; iterator--) {

                tempResult = tempResult.multiply(new BigInteger((Long.toString(iterator))));
            }
            return tempResult;
        }

        public BigInteger getResult() {

            return result;
        }

        public boolean isFinished() {

            return isFinished;
        }
    }
}
