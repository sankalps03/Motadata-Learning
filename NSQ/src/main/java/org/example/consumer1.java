package org.example;

import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;



public class consumer1
{
    
    public static void main (String[] args)
    {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        
        nsqLookup.addLookupAddress("localhost",4161);
        
        NSQConfig config = new NSQConfig();
        
        config.setMaxInFlight(1);
        
        try(NSQConsumer consumer1 = new NSQConsumer(nsqLookup,"hello","CHANNEL",(nsqMessage ->
        {
            String msg = new String(nsqMessage.getMessage());
            
            if (!msg.contains("Sankalp")){
                
                System.out.println(msg);
                
            }else {
                nsqMessage.requeue();
                
            }
            
            nsqMessage.finished();
        }),config))
        {
            consumer1.start();
            
        }catch (Exception e)
        {
            
            e.printStackTrace();
        }
        
        
    }
    
}

