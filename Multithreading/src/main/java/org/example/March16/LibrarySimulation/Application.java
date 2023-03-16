package org.example.March16.LibrarySimulation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args){

        Students[] students = null;

        Book[] books = null;

        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(100);

        try {

            books = new Book[Constants.numOfBooks];

            students = new Students[Constants.numOfStudents];

            for (int iterator = 0; iterator< Constants.numOfBooks; ++iterator ){

                books[iterator] = new Book(iterator+1);
            }

            for (int iterator =0 ; iterator<Constants.numOfStudents; ++iterator){

                students[iterator] = new Students(iterator+1,latch,books);

                executor.execute(students[iterator]);


            }

            latch.await();

            System.out.println("Reached Maximum no. of books assigned for the day");
        }catch (Exception exception){

            exception.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }
}
