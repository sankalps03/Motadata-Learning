package ChatApplicationOneToOne;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerDatagram {

    byte[] receiveData = new byte[512];
    byte[] sendData;

    InetAddress ip;

    int portNo;

    AtomicBoolean isExit = new AtomicBoolean(false);

    public static void main(String[] args) {

        ServerDatagram mServer = new ServerDatagram();

        try(DatagramSocket socket = new DatagramSocket(2019);) {


            System.out.println("Socket created Waiting for client");

            Thread readerThread = new Thread(() -> {


                mServer.read(socket);

            });

            Thread writerThread = new Thread(() -> {

                mServer.write(socket);
            });

            readerThread.setDaemon(true);

            writerThread.setDaemon(true);

            readerThread.setName("reader");

            writerThread.setName("writer");

            readerThread.start();

            writerThread.start();

            readerThread.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(DatagramSocket socket)  {


        try {


            while (!isExit.compareAndSet(true,true)){

                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(),0,receivePacket.getLength());

                if (message.equals("exit")){

                    System.out.println("Client exited");

                    isExit.getAndSet(true);

                }else {

                    System.out.println("Client :" + message);
                }

                ip = receivePacket.getAddress();

                portNo = receivePacket.getPort();

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void write(DatagramSocket socket) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             ){


            while (!isExit.compareAndSet(true,true)) {

                System.out.println("server: ");

                String sMessage = reader.readLine();

                sendData = sMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ip,portNo);

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
