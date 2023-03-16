package org.example.March16.LibrarySimulation;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CallableStudent implements Callable<String> {

    private int id ;

    private Book[] books;

    private Random random;

    private CountDownLatch latch;

    private CyclicBarrier barrier;


    public CallableStudent(int id, Book[] books){

        this.id = id;

        this.random =new Random();

        this.books = books;

    }

    @Override
    public String toString() {
        return "Students #"+ id;
    }

    @Override
    public String call() throws Exception {
        int bookId = random.nextInt(Constants.numOfBooks);

        try {

            books[bookId].read(this);


        }catch (Exception e){

            e.printStackTrace();
        }
        return String.valueOf(bookId);
    }
}

