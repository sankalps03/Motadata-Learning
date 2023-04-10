package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;


public class multiThreadedRequestResponse
{
    
    private static class Worker extends Thread
    {
        
        private ZContext context;
        
        private Worker(ZContext context)
        {
            
            this.context = context;
        }
        
        @Override
        public void run()
        {
            
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            
            socket.connect("inproc://workers");
            
            while (true)
            {
                
                String request = socket.recvStr(0);
                
                System.out.println(Thread.currentThread().getName() + " Received request: [" + request + "]");
                
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                }
                
                socket.send("world", 0);
            }
        }
    }
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            Socket clients = context.createSocket(SocketType.ROUTER);
            
            clients.bind("tcp://*:5559");
            
            Socket workers = context.createSocket(SocketType.DEALER);
            
            workers.bind("inproc://workers");
            
            for (int thread_nbr = 0; thread_nbr < 5; thread_nbr++)
            {
                Thread worker = new Worker(context);
                
                worker.start();
                
                Thread.sleep(1000);
            }
            
            ZMQ.proxy(clients, workers, null);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}