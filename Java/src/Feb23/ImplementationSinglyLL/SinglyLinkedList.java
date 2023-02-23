package Feb23.ImplementationSinglyLL;

public class SinglyLinkedList {
    private Node head;
    private int size;

    public void addToFront(Employee employee) {

        Node node = new Node(employee);

        node.setNext(head);

        head = node;

        size++;
    }

    public Node removeFromFront() {

        if (isEmpty()) {

            return null;
        }

        Node removedNode = head;

        head = head.getNext();

        size--;

        removedNode.setNext(null);

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

            System.out.print(" -> ");

            current = current.getNext();
        }
        System.out.println("null");
    }
}
