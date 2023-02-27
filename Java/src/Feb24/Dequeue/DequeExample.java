package Feb24.Dequeue;

import java.util.Deque;
import java.util.LinkedList;

public class DequeExample {
    public static void main(String[] args)
    {
        try {

            Deque<String> deque
                    = new LinkedList<String>();

            deque.add("Element 1 (Tail)");

            deque.addFirst("Element 2 (Head)");

            deque.addLast("Element 3 (Tail)");

            deque.push("Element 4 (Head)");

            deque.offer("Element 5 (Tail)");

            deque.offerFirst("Element 6 (Head)");

            System.out.println(deque + "\n");

            deque.removeFirst();

            deque.removeLast();
            System.out.println("Deque after removing "
                    + "first and last: "
                    + deque);

            System.out.println(deque);

            System.out.println(deque.pop());

            System.out.println(deque.poll());

            System.out.println(deque.pollFirst());

            System.out.println(deque.pollLast());

            System.out.println(deque);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
