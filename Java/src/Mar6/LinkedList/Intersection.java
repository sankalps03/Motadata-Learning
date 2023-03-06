package Mar6.LinkedList;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    int data;
    ListNode next;
    ListNode(int x) {
        data = x;
        next = null;
    }
}
public class Intersection {

    public static ListNode push(ListNode head, int data)
    {
        ListNode node = new ListNode(data);

        node.data = data;

        node.next = head;

        return node;
    }
    public static ListNode findIntersection(ListNode first, ListNode second)
    {

        Set<ListNode> nodes = new HashSet<>();

        while (first != null)
        {
            nodes.add(first);

            first = first.next;
        }


        while (second != null)
        {

            if (nodes.contains(second)) {

                return second;
            }
            second = second.next;
        }


        return null;
    }

    public static void main(String[] args)
    {
        ListNode first = null;

        for (int i = 5; i > 0; i--) {

            first = push(first, i);
        }

        ListNode second = null;
        for (int i = 3; i > 0; i--) {

            second = push(second, i);
        }

        second.next.next.next = first.next.next.next;

        ListNode addr = findIntersection(first, second);

        if (addr != null) {

            System.out.println("The intersection point is " + addr.data);
        }
        else {
            System.out.println("The lists do not intersect.");
        }
    }
}
