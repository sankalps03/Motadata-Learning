package org.example;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;


public class simplePublisher
{
    
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket publisher = context.createSocket(SocketType.REQ);
            
            publisher.bind("tcp://*:5559");
            
            publisher.bind("ipc://weather");
            
            Random srandom = new Random(System.currentTimeMillis());
            
            while (!Thread.currentThread().isInterrupted())
            {
                int zipcode, temperature, relhumidity;
                
                zipcode = 10000 + srandom.nextInt(10000);
                
                temperature = srandom.nextInt(215) - 80 + 1;
                
                relhumidity = srandom.nextInt(50) + 10 + 1;
                
                String update = String.format("%05d %d %d", zipcode, temperature, relhumidity);
                
//                publisher.setHWM(1);
                
                publisher.send(update,ZMQ.NOBLOCK);
                
                System.out.println(update);
            }
        }
    }
    
}
