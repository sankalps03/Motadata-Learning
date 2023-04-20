package org.example;

import java.sql.*;
import java.util.Scanner;

public class PStatement
{
    
    public static void main(String[] args)
    {
        
        
        String jdbcURL = "jdbc:h2:~/test";
        
        String username = "sankalp";
        
        String password = "sankalp";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);)
        {
            System.out.println("Connected to H2 database.");
            
            String create = "CREATE TABLE employees " + "(eNo INTEGER not NULL, " + " eName VARCHAR(255), " + " eSal VARCHAR(255), " + " eAddr VARCHAR(255), " + " PRIMARY KEY ( eNo ))";
            
            Statement statement = connection.createStatement();
            
            statement.execute(create);
            
            PreparedStatement pst = connection.prepareStatement("insert  into employees values(?,?,?,?)");
            
            Scanner sc = new Scanner(System.in);
            
            
            while (true)
            {
                System.out.println("Employee Number:");
                
                int eNo = sc.nextInt();
                
                System.out.println("Employee Name:");
                
                String eName = sc.next();
                
                System.out.println("Employee Sal:");
                
                double eSal = sc.nextDouble();
                
                System.out.println("Employee Address:");
                
                String eAddr = sc.next();
                
                pst.setInt(1, eNo);
                
                pst.setString(2, eName);
                
                pst.setDouble(3, eSal);
                
                pst.setString(4, eAddr);
                
                pst.addBatch();
                
                System.out.println("Do U want to Insert one more record[Yes/No]:");
                
                String option = sc.next();
                
                if (option.equalsIgnoreCase("No"))
                {
                    break;
                }
            }
            pst.executeBatch();
            
            System.out.println("Records inserted Successfully");
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
