package Feb24.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class EnqueueDequeue {

    public static void main(String[] args) {

        try {

            Queue<String> waitingQueue = new LinkedList<>();

            waitingQueue.add("Rajeev");

            waitingQueue.add("Chris");

            waitingQueue.add("John");

            waitingQueue.add("Mark");

            waitingQueue.add("Steven");

            System.out.println("WaitingQueue : " + waitingQueue);


            String name = waitingQueue.remove();

            System.out.println("Removed from WaitingQueue : " + name + " | New WaitingQueue : " + waitingQueue);

            name = waitingQueue.poll();

            System.out.println("Removed from WaitingQueue : " + name + " | New WaitingQueue : " + waitingQueue);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
