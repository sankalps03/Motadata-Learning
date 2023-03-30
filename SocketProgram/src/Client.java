import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;


public class Client {

    static String reading = "";

    static String writing = "";

     AtomicBoolean isExit = new AtomicBoolean(false);

    public static void main(String[] args) {


        Client client = new Client();


        System.out.println("Client is starting.......");

        try(Socket newSocket = new Socket("localhost",6666)){

            Thread readerThread = new Thread(() -> {

                client.read(newSocket);

            });

            Thread writerThread = new Thread(() -> {

                    client.write(newSocket);
            });

            readerThread.setDaemon(true);

            writerThread.setDaemon(true);

            readerThread.setName("reader");

            writerThread.setName("writer");

            writerThread.start();

            readerThread.start();

            readerThread.join();

        } catch (Exception exception) {

            exception.printStackTrace();
        }

    }
    public void read(Socket newSocket)  {


        try(DataInputStream inputFromSocket = new DataInputStream(newSocket.getInputStream())) {

        while (!isExit.compareAndSet(true,true) && !newSocket.isClosed()){


            reading = inputFromSocket.readUTF();

            if (reading.equals("exit")){

                System.out.println("Server exited");

                isExit.compareAndSet(false,true);


            }else {

            System.out.println("Server: "+reading);
            }
        }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void write(Socket newSocket) {

        try (BufferedReader typing = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream outputToSocket = new DataOutputStream(newSocket.getOutputStream())){


        while (!isExit.compareAndSet(true,true)){

            writing = typing.readLine();

            outputToSocket.writeUTF(writing);

            if (writing.equals("exit")){

                System.out.println("Exiting the chat");

                isExit.getAndSet(true);
            }else {

                System.out.println("client: "+writing);
            }

            outputToSocket.flush();
        }


        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

}

//class readerThread implements Runnable{
//
//    Socket socket;
//
//    readerThread(Socket newSocket){
//
//        this.socket = newSocket;
//
//    }
//
//    @Override
//    public void run() {
//
//        Client client = new Client();
//
//        try {
//            client.read(socket);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//}
//
//class writerThread implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//}


