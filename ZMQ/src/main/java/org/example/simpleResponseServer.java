package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;


public class simpleResponseServer
{
    
    public static void main(String[] args) throws Exception
    {
        
        try (ZContext context = new ZContext())
        {
            
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            
            socket.bind("tcp://*:5559");
            
            socket.setHWM(1);
            
            while (!Thread.currentThread().isInterrupted())
            {
                byte[] reply = socket.recv(0);
                
                System.out.println("Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]");
                
                Thread.sleep(1000);
                
                String response = "world";
                
                socket.send(response.getBytes(ZMQ.CHARSET), 1);
            }
        }
    }
}