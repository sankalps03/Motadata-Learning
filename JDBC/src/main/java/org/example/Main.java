package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    
    public static void main(String[] args) throws SQLException
    {
        
        String jdbcURL = "jdbc:h2:~/test";
        
        String username = "sankalp";
        
        String password = "sankalp";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);)
        {
            System.out.println("Connected to H2 database.");
            
            String create = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";
            
            String select = "SELECT * FROM REGISTRATION";
            
            String drop = "DROP TABLE REGISTRATION";
            
            String update = "UPDATE Registration " +
                    "SET age = 30 WHERE id in (100, 101)";
            
            String delete = "DELETE FROM Registration " +
                    "WHERE id = 101";
            
            String range = "SELECT id, first, last, age FROM Registration" +
                    " WHERE id > 101 ";
            
            
            Statement statement = connection.createStatement();
            
            statement.executeUpdate(create);
            
            System.out.println("registration table created");
            
            List<String> insert = new ArrayList<>();
            
            String sql = "INSERT INTO Registration VALUES (100, 'Zara', 'Ali', 18)";
            
            insert.add(sql);
            
            sql = "INSERT INTO Registration VALUES (101, 'Mahnaz', 'Fatma', 25)";
            
            insert.add(sql);
            
            sql = "INSERT INTO Registration VALUES (102, 'Zaid', 'Khan', 30)";
            
            insert.add(sql);
            
            sql = "INSERT INTO Registration VALUES(103, 'Sumit', 'Mittal', 28)";
            
            insert.add(sql);
            
            System.out.println("Inserting records into the table...");
            
            for (String query : insert)
            {
                
                statement.executeUpdate(query);
                
            }
            
            System.out.println("Inserted records into the table.");
            
            ResultSet resultSet = statement.executeQuery(select);
            
            System.out.println("--------------------------------------------------------");
            
            processResult(resultSet);
            
            System.out.println("--------------------------------------------------------");
            
            System.out.println("Aggregate functions");
            
            System.out.println("--------------------------------------------------------");
            
            ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) FROM REGISTRATION");
            
            if (resultSetCount.next()){
                
                System.out.println("Count " + resultSetCount.getInt(1));
            
            }
            
            ResultSet resultSetSum = statement.executeQuery("SELECT SUM(age) FROM REGISTRATION");
            
            if (resultSetSum.next()){
                
                System.out.println("SUM " +resultSetSum.getInt(1));
                
            }
            
            ResultSet resultSetAvg = statement.executeQuery("SELECT AVG(age) FROM REGISTRATION");
            
            if (resultSetAvg.next()){
                
                System.out.println("AVG "+resultSetAvg.getInt(1));
                
            }
            
            ResultSet resultSetMax = statement.executeQuery("SELECT MAX(age) FROM REGISTRATION");
            
            if (resultSetMax.next()){
                
                System.out.println("MAX "+resultSetMax.getInt(1));
                
            }
            
            ResultSet resultSetMin = statement.executeQuery("SELECT MIN(age) FROM REGISTRATION");
            
            if (resultSetMin.next()){
                
                System.out.println("MIN "+resultSetMin.getInt(1));
                
            }
            
            System.out.println("--------------------------------------------------------");
            
            ResultSet resultSetOrdered = statement.executeQuery(select + " ORDER BY first ASC");
            
            System.out.println("Table in ascending order by name");
            
            System.out.println("--------------------------------------------------------");
            
            processResult(resultSetOrdered);
            
            
            System.out.println("--------------------------------------------------------");
            
            System.out.println("Get in range (id > 101)");
            
            ResultSet resultSetRange = statement.executeQuery(range);
            
            System.out.println("--------------------------------------------------------");
            
            processResult(resultSetRange);
            
            System.out.println("--------------------------------------------------------");
            
            statement.executeUpdate(update);
            
            System.out.println("Registration table updated");
            
            ResultSet resultSetUpdate = statement.executeQuery(select);
            
            System.out.println("--------------------------------------------------------");
            
            processResult(resultSetUpdate);
            
            System.out.println("--------------------------------------------------------");
            
            statement.executeUpdate(delete);
            
            System.out.println("One entry deleted");
            
            ResultSet resultSetDelete = statement.executeQuery(select);
            
            System.out.println("--------------------------------------------------------");
            
            processResult(resultSetDelete);
            
            System.out.println("--------------------------------------------------------");
            
            statement.executeUpdate(drop);
            
            System.out.println("Registration table dropped");
            
        }
    }
    
    public static void processResult(ResultSet resultSet)
    {
        
        try
        {
            
            int count = 0;
            
            while (resultSet.next())
            {
                count++;
                
                int ID = resultSet.getInt("ID");
                
                String name = resultSet.getString("first") + resultSet.getString("last");
                
                int age = resultSet.getInt("age");
                
                System.out.println("Guest #" + count + ": " + ID + ", " + name + ", " + age);
            }
            
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
    }
}