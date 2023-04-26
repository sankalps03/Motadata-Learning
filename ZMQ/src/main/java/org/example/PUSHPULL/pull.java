package org.example.PUSHPULL;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class pull
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            
            receiver.setHWM(2);
            
            
            if (receiver.connect("tcp://localhost:5557"))
            {
                
                System.out.println("connected");
            }
            while (!Thread.currentThread().isInterrupted())
            {
                String string = new String(receiver.recv(0), ZMQ.CHARSET).trim();
                
                System.out.println(string);
                
                Thread.sleep(1000);
                
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        
    }
}
