package org.example;

import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class simpleSubscriber
{
    
    public static void main(String[] args)
    {
        
        try (ZContext context = new ZContext())
        {
            System.out.println("Collecting updates from weather server");
            
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            
            subscriber.connect("tcp://localhost:5559");
            
//            subscriber.setHWM(100);
            
            System.out.println("connected");
            
            String filter = (args.length > 0) ? args[0] : "10001 ";
            
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            
            int update_nbr;
            
            long total_temp = 0;
            
            for (update_nbr = 1; update_nbr <= 100; update_nbr++)
            {
                String string = subscriber.recvStr(0).trim();
                
                StringTokenizer sscanf = new StringTokenizer(string, " ");
                
                int zipcode = Integer.parseInt(sscanf.nextToken());
                
                int temperature = Integer.parseInt(sscanf.nextToken());
                
                int relhumidity = Integer.parseInt(sscanf.nextToken());
                
                total_temp += temperature;
                
                System.out.printf("Average temperature for "+zipcode+ " '%s' was %d.%n", filter, (int) (total_temp / update_nbr) + "humidity: "+ relhumidity);
                
            }
            
        }
        
    }
    
}
