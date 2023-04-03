package org.example.chatApplicationOneToOne.ServerSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class client {
    public static void main(String[] args) {


        System.out.println("Client is starting.......");

        try(
            Socket newSocket = new Socket("localhost",6666);
            DataInputStream inputFromSocket = new DataInputStream(newSocket.getInputStream());
            DataOutputStream outputToSocket = new DataOutputStream(newSocket.getOutputStream());
            BufferedReader typing = new BufferedReader(new InputStreamReader(System.in));
        ) {

            String reading = "";

            String writing = "";

            while (!writing.equals("exit")){

                reading = inputFromSocket.readUTF();

                System.out.println("Server: "+reading);

                writing = typing.lines().toString();

                outputToSocket.writeUTF(writing);

                outputToSocket.flush();

            }

        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }
}
