package Groupchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class client {

    private Socket socket;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private String username;

    public  client(Socket socket, String username){

        try{
            this.socket =socket;

            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.username = username;
        }
        catch (Exception e){

            closeEverything(socket,bufferedReader, bufferedWriter);

            e.printStackTrace();
        }
    }

    public  void sendMessage(BufferedReader inputReader){

        try {
             bufferedWriter.write(username);
             bufferedWriter.newLine();
             bufferedWriter.flush();

             while (socket.isConnected()){

                 String message = inputReader.readLine();

                 if (message.equals("exit")){

                     System.out.println("Exiting the chat");

                     break;
                 }

                 bufferedWriter.write(username+": "+ message);

                 bufferedWriter.newLine();

                 bufferedWriter.flush();

             }


        }catch (Exception e){

            closeEverything(socket, bufferedReader, bufferedWriter);

            e.printStackTrace();
        }
    }

    public void listen(){

        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {

                String message;

                while (socket.isConnected()){

                    try {
                        message = bufferedReader.readLine();

                        System.out.println(message);

                    } catch (Exception e){

                        closeEverything(socket, bufferedReader, bufferedWriter);

                        e.printStackTrace();
                    }
                }

            }
        });

        reader.setName("Reader");

        reader.setDaemon(true);

        reader.start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        try {
            if (bufferedReader != null){

                bufferedReader.close();
            }

            if (bufferedWriter != null){

                bufferedWriter.close();
            }

            if (socket != null){

                socket.close();
            }
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try(BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))){

            System.out.println("Enter your username");

            String username = inputReader.readLine();

            Socket socket1 = new Socket("localhost", 3333);

            client clientObj = new client(socket1, username);

            clientObj.listen();

            clientObj.sendMessage(inputReader);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
