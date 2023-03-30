import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class server {

     static String reading = "";

     static String writing = "";

      AtomicBoolean isExit = new AtomicBoolean(false);

    public static void main(String[] args) {

        server mServer = new server();

        System.out.println("Server is starting.......");

        try(ServerSocket newServer = new ServerSocket(6666);Socket newSocket = newServer.accept()){

            System.out.println("Connected");

            Thread readerThread = new Thread(() -> {


                mServer.read(newSocket);

            });

            Thread writerThread = new Thread(() -> {

                mServer.write(newSocket);
            });

            readerThread.setDaemon(true);

            writerThread.setDaemon(true);

            readerThread.setName("reader");

            writerThread.setName("writer");

            readerThread.start();

            writerThread.start();

            readerThread.join();

        } catch (Exception exception) {

            exception.printStackTrace();
        }

    }
    public void read(Socket newSocket)  {


        try(DataInputStream inputFromSocket = new DataInputStream(newSocket.getInputStream())) {


            while (!isExit.get() && !newSocket.isClosed()){


                reading = inputFromSocket.readUTF();

                if (reading.equals("exit")){

                    isExit.compareAndSet(false,true);

                    System.out.println("Client exited");

                }
                else {

                System.out.println("client: "+reading);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void write(Socket newSocket) {

        try (BufferedReader typing = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream outputToSocket = new DataOutputStream(newSocket.getOutputStream())){


            while (!isExit.get()){

                writing = typing.readLine();

                outputToSocket.writeUTF(writing);

                if (writing.equals("exit")){

                    isExit.getAndSet(true);

                    System.out.println("Exiting the chat");
                }else {

                System.out.println("Server: "+writing);

                }

                outputToSocket.flush();
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}
