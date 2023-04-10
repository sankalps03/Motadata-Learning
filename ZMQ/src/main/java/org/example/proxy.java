package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;

public class proxy
{
    
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            
            Socket frontend = context.createSocket(SocketType.ROUTER);
            
            frontend.bind("tcp://*:5559");
            
            Socket backend = context.createSocket(SocketType.DEALER);
            
            backend.bind("tcp://*:5560");
            
            ZMQ.proxy(frontend, backend, null);
        }
    }
}
