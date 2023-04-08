package org.example;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class ventilator
{
    
    public static void main(String[] args) throws Exception
    {
        
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);
            
            sender.bind("tcp://*:5557");
            
            ZMQ.Socket sink = context.createSocket(SocketType.PUSH);
            
            sink.connect("tcp://localhost:5558");
            
            System.out.println("Press Enter when the workers are ready: ");
            
            System.in.read();
            
            System.out.println("Sending tasks to workers\n");
            
            sink.send("0", 0);
            
            Random srandom = new Random(System.currentTimeMillis());
            
            int task_nbr;
            
            int total_msec = 0;
            
            for (task_nbr = 0; task_nbr < 100; task_nbr++)
            {
                int workload;
                
                workload = srandom.nextInt(100) + 1;
                
                total_msec += workload;
                
                System.out.print(workload + ".");
                
                String string = String.format("%d", workload);
                
                sender.send(string, 0);
            }
            System.out.println("Total expected cost: " + total_msec + " msec");
            
            Thread.sleep(1000);
        }
    }
    
}
