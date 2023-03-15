package org.example.March14;

import java.math.BigInteger;
import java.util.Random;

public class MultithreadedCalculation {

    public static void main(String[] args){

        MultithreadedCalculation calculate = new MultithreadedCalculation();

        Random random = new Random();

        BigInteger base1 = new BigInteger(4, random);

        BigInteger power1 = new BigInteger(4, random);

        BigInteger base2 = new BigInteger(4, random);

        BigInteger power2 = new BigInteger(4, random);

        try{

        System.out.println(calculate.calculateResult(base1,power1,base2,power2));

        }catch (Exception exception){

            exception.printStackTrace();
        }
    }
    public BigInteger calculateResult(BigInteger base1,
                                      BigInteger power1,
                                      BigInteger base2,
                                      BigInteger power2) {

        BigInteger result;

        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);

        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();

        thread2.start();

        try {

            thread1.join();

            thread2.join();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        result = thread1.getResult().add(thread2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {

            this.base = base;

            this.power = power;
        }

        @Override
        public void run() {

            for(BigInteger num = BigInteger.ZERO;

                num.compareTo(power) !=0;

                num = num.add(BigInteger.ONE)) {

                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {

            return result;
        }
    }
}
