package Feb24.Queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class IteratingQueue {

    public static void main(String[] args) {

        try {

            Queue<String> waitingQueue = new LinkedList<>();

            waitingQueue.add("John");

            waitingQueue.add("Brad");

            waitingQueue.add("Angelina");

            waitingQueue.add("Julia");

            System.out.println("Iterating over a Queue using forEach()");

            waitingQueue.forEach(name -> {

                System.out.println(name);

            });

            System.out.println("Iterating over a Queue using iterator()");

            Iterator<String> waitingQueueIterator = waitingQueue.iterator();

            while (waitingQueueIterator.hasNext()) {

                String name = waitingQueueIterator.next();

                System.out.println(name);
            }

            System.out.println("Iterating over a Queue using iterator() and forEachRemaining()");

            waitingQueueIterator = waitingQueue.iterator();

            waitingQueueIterator.forEachRemaining(name -> {

                System.out.println(name);

            });

            System.out.println("Iterating over a Queue using simple for-each loop");
            for (String name : waitingQueue) {

                System.out.println(name);
            }
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
