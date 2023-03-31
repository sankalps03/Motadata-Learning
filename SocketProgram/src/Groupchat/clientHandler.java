package Groupchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class clientHandler implements Runnable{

    public static ArrayList<clientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private String clientUsername;

    public clientHandler(Socket socket){

        try{
            this.socket = socket;

            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.clientUsername = bufferedReader.readLine();

            clientHandlers.add(this);

            broadcastMessage("SERVER: " + clientUsername +" has entered the chat");

        }
        catch (Exception e){

            closeEverything(socket,bufferedReader,bufferedWriter);

            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        String messageFromClient;

        while (socket.isConnected()){

            try {

                    messageFromClient = bufferedReader.readLine();

                    broadcastMessage(messageFromClient);

            }catch (Exception e){

                closeEverything(socket,bufferedReader,bufferedWriter);

                break;
            }
        }



    }

    public  void broadcastMessage(String message){
        for (clientHandler clientHandler: clientHandlers){

            try{

                if(!clientHandler.clientUsername.equals(clientUsername)){

                    clientHandler.bufferedWriter.write(message);

                    clientHandler.bufferedWriter.newLine();

                    clientHandler.bufferedWriter.flush();
                }
            }catch (Exception e){

                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }


    }

    public void removeClientHandler(){

        clientHandlers.remove(this);

        broadcastMessage("SERVER: "+ clientUsername+ " has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        removeClientHandler();

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
}
