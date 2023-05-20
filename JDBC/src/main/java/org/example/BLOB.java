package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BLOB {
    
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            
            System.out.println("driver loaded");
            
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sankalp", "sankalp");
            
            System.out.println("got database connection");
            
            String sql = "Create table BLOB (u_name VARCHAR(50), imagefile BLOB)";
            
            Statement statement = connection.createStatement();
            
//             statement.execute(sql);
            
            System.out.println("Created table image.");
            
            String sqlQuery="insert into BLOB values(?,?)";
            
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            
            ps.setString(1,"Katrina");
            
            File f = new File("src/main/resources/katrina.jpg");
            
            System.out.println("INSERTED");
            
            FileInputStream fis = new FileInputStream(f);
            
            ps.setBinaryStream(2,fis);
            
            
            
            System.out.println("inserting image from :"+f.getAbsolutePath());
            
            
            
            int updateCount=ps.executeUpdate();
            
            if(updateCount==1)
            {
                System.out.println("Record Inserted");
            }
            else
            {
                System.out.println("Record Not Inserted");
            }
            
            
        }
        
        catch (Exception e){
        
        }
    }
}
