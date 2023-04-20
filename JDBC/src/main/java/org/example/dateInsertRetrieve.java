package org.example;

import java.sql.*;
import java.text.*;
import java.util.Scanner;

public class dateInsertRetrieve
{
    
    public static void main(String[] args)
    {
        
        String jdbcURL = "jdbc:h2:~/test";
        
        String username = "sankalp";
        
        String password = "sankalp";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);)
        {
            System.out.println("Connected to H2 database.");
            
            String create = "CREATE TABLE persons " + "(eNo INTEGER not NULL, " + " eName VARCHAR(255), " + " eDob date, " + " eDoj date, " + " PRIMARY KEY ( eNo ))";
            
            Statement statement = connection.createStatement();
            
//            statement   .execute(create);
            
            String sqlQuery = "insert into persons values(?,?,?,?)";
            
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            
            while (true)
            {
                
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Enter Person No:");
                
                int uNo = sc.nextInt();
                
                System.out.println("Enter Person Name:");
                
                String uName = sc.next();
                
                System.out.println("Enter DOB(dd-mm-yy):");
                
                String uDob = sc.next();
                
                System.out.println("Enter DOJ(yyyy-mm-dd):");
                
                String uDoj = sc.next();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
                
                java.util.Date sDob = sdf.parse(uDob);
                
                java.sql.Date sQDob = new java.sql.Date(sDob.getTime());
                
                java.sql.Date sQDoj = java.sql.Date.valueOf(uDoj);
                
                ps.setInt(1, uNo);
                
                ps.setString(2, uName);
                
                ps.setDate(3, sQDob);
                
                ps.setDate(4, sQDoj);
                
                ps.addBatch();
                
                System.out.println("Do U want to Insert one more record[Yes/No]:");
                
                String option = sc.next();
                
                if (option.equalsIgnoreCase("No"))
                {
                    break;
                }
                
            }
            
            ps.executeBatch();
            
            
            PreparedStatement pStatement = connection.prepareStatement("select * from persons");
            
            ResultSet rs =pStatement.executeQuery();
            
            SimpleDateFormat sdf= new SimpleDateFormat("dd-MMM-yyyy");
            
            while(rs.next())
            {
                Date d=rs.getDate("eDob");
                String s=sdf.format(d);
                System.out.println(s);
            }
                
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
