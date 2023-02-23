package Feb23.ImplmentationDoublyLL;

import Feb23.ImplementationSinglyLL.Node;

import Feb23.ImplementationSinglyLL.Employee;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void addToFront(Employee employee) {

        Node node = new Node(employee);

        if (head == null) {

            tail = node;
        }
        else {

            head.setPrevious(node);

            node.setNext(head);
        }

        head = node;

        size++;
    }

    public void addToEnd(Employee employee) {

        Node node = new Node(employee);

        if (tail == null) {

            head = node;
        }
        else {

            tail.setNext(node);

            node.setPrevious(tail);
        }

        tail = node;

        size++;
    }

    public Node removeFromFront() {

        if (isEmpty()) {

            return null;
        }

        Node removedNode = head;

        if (head.getNext() == null) {

            tail = null;
        }
        else {
            head.getNext().setPrevious(null);
        }

        head = head.getNext();

        size--;

        removedNode.setNext(null);

        return removedNode;
    }

    public Node removeFromEnd() {

        if (isEmpty()) {

            return null;
        }

        Node removedNode = tail;

        if (tail.getPrevious() == null) {

            head = null;
        }
        else {
            tail.getPrevious().setNext(null);
        }

        tail = tail.getPrevious();

        size--;

        removedNode.setPrevious(null);

        return removedNode;
    }

    public int getSize() {

        return size;
    }

    public boolean isEmpty() {

        return head == null;
    }

    public void printList() {

        Node current = head;

        System.out.print("HEAD -> ");

        while (current != null) {

            System.out.print(current);

            System.out.print(" <=> ");

            current = current.getNext();
        }
        System.out.println("null");
    }
}
