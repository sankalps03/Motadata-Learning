package Mar7;

import java.util.Arrays;

public class DesignHashMap {

    static class ListNode{

        int key,value;
        ListNode next;

        ListNode(int key,int value){
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args){
        try {

            DesignHashMap myHashMap = new DesignHashMap();

            myHashMap.put(1, 1);

            myHashMap.put(2, 2);

            System.out.println(myHashMap.get(1));

            System.out.println(myHashMap.get(2));

            System.out.println(myHashMap.get(3));

            myHashMap.put(2, 1);

            System.out.println(myHashMap.get(2));

            myHashMap.remove(2);

            System.out.println(myHashMap.get(2));

        }
        catch(Exception exception){

            exception.printStackTrace();
        }
    }
        ListNode[] nodes;
    DesignHashMap(){

        nodes= new ListNode[100];

    }
    public void put(int key, int value) {

        int index = getIndex(key);

        ListNode previous = findElement(index,key);

        if(previous.next==null)

            previous.next = new ListNode(key,value);
        else
            previous.next.value = value;

    }

    private ListNode findElement(int index, int key) {

        if (nodes[index] == null)
            return nodes[index] = new ListNode(-1,-1);

        ListNode previous = nodes[index];

        while (previous.next != null && previous.next.key != key){

            previous = previous.next;
        }
        return previous;
    }

    public int get(int key) {

        int index = getIndex(key);

        ListNode previous = findElement(index,key);

        if(previous.next==null)

            return  -1;

        else

            return previous.next.value;

    }

    public void remove(int key) {

        int index = getIndex(key);

        ListNode previous = findElement(index,key);

        if(previous.next !=null)

            previous.next = previous.next.next;

    }

    public int getIndex(int key){

        return Integer.hashCode(key)%nodes.length;
    }
}
