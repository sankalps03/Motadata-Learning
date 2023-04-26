package org.example;

import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;


public class consumer
{
    
    public static void main(String[] args)
    {
        
        NSQLookup nsqLookup = new DefaultNSQLookup();
        
        nsqLookup.addLookupAddress("localhost", 4161);
        
        NSQConfig config = new NSQConfig();

        
        
        NSQConsumer consumer = new NSQConsumer(nsqLookup, "hello", "CHANNEL", (nsqMessage ->
        {
            String msg = new String(nsqMessage.getMessage());

                System.out.println(msg);

            nsqMessage.finished();
            
            
            
        }));
        
        
            consumer.start();
        
        
        
    }
    
}
