package Mar7;

public class CircularArrayLoop {
public static void main(String[] args) throws InterruptedException {

    Integer i = new  Integer(10);
    synchronized (i) {
        i.wait(10000);
    }
}
}
