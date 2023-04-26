package org.example;


import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class worker2
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            
            receiver.connect("tcp://localhost:5557");
            
            
            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);
            receiver.setHWM(1);
            
            sender.connect("tcp://localhost:5558");
            
            while (!Thread.currentThread().isInterrupted())
            {
                String string = new String(receiver.recv(0), ZMQ.CHARSET).trim();
                
                long msec = Long.parseLong(string);
                
                System.out.flush();
                
                System.out.println(string);
                
                Thread.sleep(5000);
                
                sender.send(ZMQ.MESSAGE_SEPARATOR, 0);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
}

