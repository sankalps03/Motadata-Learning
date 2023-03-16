package org.example.March16.LibrarySimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApplicationCallable {

    public static void main(String[] args){

        CallableStudent[] students = null;

        Book[] books = null;

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<String>> list = new ArrayList<>();

        try {

            books = new Book[Constants.numOfBooks];

            students = new CallableStudent[Constants.numOfStudents];

            for (int iterator = 0; iterator< Constants.numOfBooks; ++iterator ){

                books[iterator] = new Book(iterator+1);
            }

            for (int iterator =0 ; iterator<Constants.numOfStudents; ++iterator){

                students[iterator] = new CallableStudent(iterator+1,books);

                Future<String> bookId =executor.submit(students[iterator]);

                list.add(bookId);


            }

            Thread.sleep(1000);

            System.out.println("List of Book read");

            for(Future<String> future : list){

                try {

                    System.out.println(future.get());

                }catch (Exception exception){

                    exception.printStackTrace();
                }
            }


        }catch (Exception exception){

            exception.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }
}
