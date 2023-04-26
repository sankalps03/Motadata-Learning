package ChatApplicationOneToOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicBoolean;

public class clientDatagram {

    byte[] receiveData = new byte[7];
    byte[] sendData;

    AtomicBoolean isExit = new AtomicBoolean(false);

    public static void main(String[] args) {

        clientDatagram client = new clientDatagram();

        try(DatagramSocket socket = new DatagramSocket()) {

            System.out.println("Socket created Waiting for server");

            Thread readerThread = new Thread(() -> {

                client.read(socket);

            });

            Thread writerThread = new Thread(() -> {

                client.write(socket);
            });

            readerThread.setDaemon(true);

            writerThread.setDaemon(true);

            readerThread.setName("reader");

            writerThread.setName("writer");

            writerThread.start();

            readerThread.start();

            readerThread.join();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(DatagramSocket socket)  {


        try {


            while (isExit.compareAndSet(false,false)){

                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(),0,receivePacket.getLength());

                if (message.equals("exit")){

                    System.out.println("sever exited");

                    isExit.getAndSet(true);

                }else {

                System.out.println("Server :" + message);
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void write(DatagramSocket socket) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ){

            InetAddress ip = InetAddress.getLocalHost();


            while (isExit.compareAndSet(false,false)) {

                System.out.println("Client: ");

                String sMessage = reader.readLine();

                sendData = sMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ip,2019);

                socket.send(sendPacket);

                if (sMessage.equals("exit")){

                    System.out.println("Exiting the chat");

                    isExit.getAndSet(true);

                }

            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

}
