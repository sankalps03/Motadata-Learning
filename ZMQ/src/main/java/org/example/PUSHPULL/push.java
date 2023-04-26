package org.example.PUSHPULL;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class push
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);
            
            sender.bind("tcp://*:5557");
            
            System.out.println("Press Enter when the workers are ready: ");
            
            System.in.read();
            
            for (int task_nbr = 0; task_nbr < 30; task_nbr++)
            {
                
                String string = (String.valueOf(task_nbr));
                
                if (!sender.send(string,ZMQ.NOBLOCK))
                {
                    System.out.println(string);
                }
            }
            
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
