package org.example.chatApplicationOneToOne.ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {


        System.out.println("Server is starting.......");

        try(ServerSocket newServer = new ServerSocket(6666);
            Socket newSocket = newServer.accept();
            DataInputStream inputFromSocket = new DataInputStream(newSocket.getInputStream());
            DataOutputStream outputToSocket = new DataOutputStream(newSocket.getOutputStream());
            BufferedReader typing = new BufferedReader(new InputStreamReader(System.in));
) {

            String reading = "";

            String writing = "";

            while (!writing.equals("exit")){

                reading = inputFromSocket.readUTF();

                System.out.println("client: "+reading);

                writing = typing.lines().toString();

                outputToSocket.writeUTF(writing);

                outputToSocket.flush();

            }

        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }
}
