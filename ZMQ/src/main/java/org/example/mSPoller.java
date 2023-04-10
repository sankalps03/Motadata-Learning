package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class mSPoller
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            
            receiver.connect("tcp://localhost:5557");
            
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            
            subscriber.connect("tcp://localhost:5559");
            
            subscriber.subscribe("10001 ".getBytes(ZMQ.CHARSET));
            
            ZMQ.Poller items = context.createPoller(2);
            
            items.register(receiver, ZMQ.Poller.POLLIN);
            
            items.register(subscriber, ZMQ.Poller.POLLIN);
            
            while (!Thread.currentThread().isInterrupted())
            {
                byte[] message;
                
                items.poll();
                
                if (items.pollin(0))
                {
                    message = receiver.recv(0);
                    
                    System.out.println("Process task");
                }
                if (items.pollin(1))
                {
                    message = subscriber.recv(0);
                    
                    System.out.println("Process weather update");
                }
            }
        }
    }
}


