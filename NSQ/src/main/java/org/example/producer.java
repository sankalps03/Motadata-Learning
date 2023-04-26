package org.example;

import com.github.brainlag.nsq.*;

import java.util.ArrayList;
import java.util.List;


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
            
            while (true )
            {
                String message = "harsh "+ count;
                
                String message1 = "This is Harsh "+ count;
                
                List<byte[]> list = new ArrayList<>();
                list.add(message.getBytes());
                list.add(message1.getBytes());
                
//                NSQCommand.multiPublish("hello", list);
                
                
                  producer.produce("hello",(message.getBytes()));
//
//                producer.produce("Harsh" ,message1.getBytes() );
                
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