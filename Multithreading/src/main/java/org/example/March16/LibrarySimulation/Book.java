package org.example.March16.LibrarySimulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

    private int id;

    private Lock lock;

    public Book(int id){

        this.id = id;

        this.lock =new ReentrantLock();
    }

    public void read(Students student){
        try{

            if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {

                System.out.println(student + " starts reading " + this);

                Thread.sleep(20);
            }else {

                System.out.println(this+" book is assigned to other student ");
            }


        }catch (Exception exception){

            exception.printStackTrace();
        }

        finally {
            try {

                lock.unlock();

            }catch (Exception e){

                e.printStackTrace();
            }

            System.out.println(student+" has finished reading "+this);
        }
    }

    public void secondRead(Students student){
        try{

            if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {

                Thread.sleep(1000);

                System.out.println(student + " starts reading " + this + " second time");


            }else {

                System.out.println(this+" book is assigned to other student ");
            }


        }catch (Exception exception){

            exception.printStackTrace();
        }

        finally {
            try {

                lock.unlock();

            }catch (Exception e){

                e.printStackTrace();
            }

            System.out.println(student+" has finished reading "+this+" second time");
        }
    }
    public void read(CallableStudent student){
        try{

            if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {

                System.out.println(student + " starts reading " + this);

                Thread.sleep(20);
            }else {

                System.out.println(this+" book is assigned to other student ");
            }


        }catch (Exception exception){

            exception.printStackTrace();
        }

        finally {
            try {

                lock.unlock();

            }catch (Exception e){

                e.printStackTrace();
            }

            System.out.println(student+" has finished reading "+this);
        }
    }

    @Override
    public String toString() {
        return "Book " + id;
    }
}
