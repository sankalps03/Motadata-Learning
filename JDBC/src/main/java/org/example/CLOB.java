package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class CLOB {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            
            System.out.println("driver loaded");
            
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sankalp", "sankalp");
            
            System.out.println("got database connection");
            
            String sql = "Create table CLOB (city_name VARCHAR(50), TEXT_File CLOB)";
            
            Statement statement = connection.createStatement();
            
             statement.execute(sql);
            
            System.out.println("Created table text.");
            
            String sqlQuery="insert into CLOB values(?,?)";
            
            
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            
            ps.setString(1,"Hyderabad");
            
            File f = new File("src/main/resources/hydLatesthistory1.txt");
            
            FileReader fr = new FileReader(f);
            
            ps.setCharacterStream(2,fr);
            
            System.out.println("file is inserting from :"+f.getAbsolutePath());
            
            int updateCount=ps.executeUpdate();
            
            if(updateCount==1)
            {
                System.out.println("Record Inserted");
            }
            else
            {
                System.out.println("Record Not Inserted");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
