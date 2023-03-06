package Mar4.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCalls {
    Queue<Integer> queue;
    public RecentCalls() {

        queue = new LinkedList<>();
    }

    public static void main(String[] args){

        RecentCalls obj = new RecentCalls();

        System.out.println(obj.ping(1));

        System.out.println(obj.ping(100));

        System.out.println(obj.ping(1000));

        System.out.println(obj.ping(3000));

        System.out.println(obj.ping(3001));

        System.out.println(obj.ping(3005));

        System.out.println(obj.ping(3101));

        System.out.println(obj.ping(4000));

    }

    public int ping(int t) {

        queue.offer(t);

        while (!queue.isEmpty() && queue.peek() < t - 3000) {

            queue.poll();
        }
        return queue.size();
    }
}

