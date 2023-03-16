package org.example.March16.LibrarySimulation;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Students implements Runnable{;

    private int id ;

    private Book[] books;

    private Random random;

    private CountDownLatch latch;

    private CyclicBarrier barrier;


    public Students(int id, CyclicBarrier barrier,Book[] books){

        this.id = id;

        this.random =new Random();

        this.books = books;

        this.barrier = barrier;

    }

    public Students(int id,CountDownLatch latch,Book[] books){

        this.latch = latch;

        this.id = id;

        this.random =new Random();

        this.books = books;


    }
    @Override
    public void run() {


            int bookId = random.nextInt(Constants.numOfBooks);

            try {

                books[bookId].read(this);

//                latch.countDown();

                barrier.await();

                books[bookId].secondRead(this);

            }catch (Exception e){

                e.printStackTrace();
            }


    }

    @Override
    public String toString() {
        return "Students #"+ id;
    }
}
