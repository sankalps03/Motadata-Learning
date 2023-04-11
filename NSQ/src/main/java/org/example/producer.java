package org.example;

import com.github.brainlag.nsq.*;


public class producer
{
    
    public static void main (String[] args)
    {
        
        NSQProducer producer = new NSQProducer();
        
        producer.addAddress("localhost",4150);
        
        try
        {
            producer.start();
            
            int count = 0;
            
            while ( true )
            {
                String message = "This is sankalp "+ count;
                
                String message1 = "This is Harsh "+ count;
                
                
                producer.produce("Sankalp",(message.getBytes()));
                
                producer.produce("Harsh" ,message1.getBytes() );
                
                count++;
                
                System.out.println(message);
            }
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
    }
}