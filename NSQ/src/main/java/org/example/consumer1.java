package org.example;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;



public class consumer1
{
    
    public static void main (String[] args)
    {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        
        nsqLookup.addLookupAddress("localhost",4161);
        
        NSQConsumer consumer1 = new NSQConsumer(nsqLookup,"Harsh","CHANNEL",(nsqMessage ->
        {
            String msg = new String(nsqMessage.getMessage());
            
            System.out.println(msg);
            
            nsqMessage.finished();
        }));
        
        consumer1.start();
    }
    
}

