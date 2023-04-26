package org.example;


import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class simpleRequestClient
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");
            
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            
            socket.connect("tcp://localhost:5559");
            
            socket.setHWM(1);
            
            for (int requestNbr = 0; requestNbr != 100; requestNbr++)
            {
                String request = "Hello";
                
                System.out.println("Sending Hello " + requestNbr);
                
                socket.send(request.getBytes(ZMQ.CHARSET), 1);
                
                byte[] reply = socket.recv(0);
                
                System.out.println("Received " + new String(reply, ZMQ.CHARSET) + " " + requestNbr);
            }
        }
    }
}
