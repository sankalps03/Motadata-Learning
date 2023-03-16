package org.example.March16.LibrarySimulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;


public class ApplicationUsingCyclicBarrier {
    public static void main(String[] args){

        Students[] students = null;

        Book[] books = null;

        ExecutorService executor = Executors.newCachedThreadPool();

        CyclicBarrier barrier = new CyclicBarrier(100);

        try {

            books = new Book[Constants.numOfBooks];

            students = new Students[Constants.numOfStudents];

            for (int iterator = 0; iterator< Constants.numOfBooks; ++iterator ){

                books[iterator] = new Book(iterator+1);
            }

            for (int iterator =0 ; iterator<Constants.numOfStudents; ++iterator){

                students[iterator] = new Students(iterator+1,barrier,books);

                executor.execute(students[iterator]);


            }

        }catch (Exception exception){

            exception.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }
}
