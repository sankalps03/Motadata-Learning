package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;


public class rRBroker
{
    
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            
            Socket frontend = context.createSocket(SocketType.ROUTER);
            
            Socket backend = context.createSocket(SocketType.DEALER);
            
            frontend.bind("tcp://*:5559");
            
            backend.bind("tcp://*:5560");
            
            System.out.println("launch and connect broker.");
            
            Poller items = context.createPoller(2);
            
            items.register(frontend, Poller.POLLIN);
            
            items.register(backend, Poller.POLLIN);
            
            boolean more = false;
            
            byte[] message;
            
            while (!Thread.currentThread().isInterrupted()) {
                
                items.poll();
                
                if (items.pollin(0)) {
                    
                    while (true) {
                        
                        message = frontend.recv(0);
                        
                        more = frontend.hasReceiveMore();
                        
                        backend.send(message, more ? ZMQ.SNDMORE : 0);
                        
                        if (!more) {
                            
                            break;
                        }
                    }
                }
                
                if (items.pollin(1)) {
                    
                    while (true) {
                        
                        message = backend.recv(0);
                        
                        more = backend.hasReceiveMore();
                        
                        frontend.send(message, more ? ZMQ.SNDMORE : 0);
                        
                        if (!more) {
                            
                            break;
                        }
                    }
                }
            }
        }
    }
}
