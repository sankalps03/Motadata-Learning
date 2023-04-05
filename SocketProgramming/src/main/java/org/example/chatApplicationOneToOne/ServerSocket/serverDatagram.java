package org.example.chatApplicationOneToOne.ServerSocket;

import java.io.IOException;
import java.net.*;

public class serverDatagram {

    public static void sankalp() throws IOException {

        DatagramSocket ds = new DatagramSocket();
        String str = "Welcome java";
        InetAddress ip = InetAddress.getByName("0.0.0.0");

        DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);
        ds.send(dp);
        ds.close();
    }

    public static void main(String[] args) throws Exception {




    }

     static void client() throws Exception {
        DatagramSocket ds = new DatagramSocket(3000);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        ds.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength());
        System.out.println(str);
        ds.close();
}
}

