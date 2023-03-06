package Mar6.LinkedList;

import java.util.Stack;

public class PalindromeList {
    static class Node {
        int data;
        Node next;

        Node(int value) {

            data = value;

            next = null;
        }
    }
        Node head;


        public void insertAtLast(int data) {

            Node newNode = new Node(data);

            if (head == null) {

                head = newNode;

                return;
            }
            newNode.next = null;


            Node temp = head;

            while (temp.next != null) {

                temp = temp.next;
            }
            temp.next = newNode;
        }

        public void printList(Node head) {

            System.out.println("Printing the linked list");

            Node temp = head;

            while (temp != null) {

                System.out.print(temp.data + " ");

                temp = temp.next;
            }


            System.out.println();
        }

        public boolean isPalindrome(Node head) {

            Stack<Integer> myStack = new Stack<>();

            Node temp = head;

            boolean status = false;

            while (temp != null) {

                myStack.push(temp.data);

                temp = temp.next;
            }
            temp = head;

            while (temp != null) {

                int element = myStack.pop();

                if (temp.data == element) {

                    status = true;

                    temp = temp.next;

                } else {

                    status = false;

                    break;
                }
            }

            return status;

        }

        public static void main(String[] args) {
            PalindromeList list = new PalindromeList();

            list.head = new Node(1);

            list.insertAtLast(2);

            list.insertAtLast(1);

            list.insertAtLast(2);

            list.insertAtLast(1);

            list.printList(list.head);

            if (list.isPalindrome(list.head)) {

                System.out.println("Palindrome Linked List");

            } else {

                System.out.println("Not a Palindrome Linked List");
            }


        }
}
