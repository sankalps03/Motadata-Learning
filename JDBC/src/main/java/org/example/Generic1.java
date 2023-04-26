package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class Generic1
{
    
    private static final String SELECT = "SELECT";
    
    private static final String INSERT = "INSERT";
    
    
    public static void main(String[] args)
    {
        
        String jdbcURL = "jdbc:h2:~/test";
        
        String username = "sankalp";
        
        String password = "sankalp";
        
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); Connection connection = DriverManager.getConnection(jdbcURL, username, password))
        {
            
            ResultSet resultSet = connection.getMetaData().getTables(connection.getCatalog(), "", null, new String[]{"TABLE"});
            
            ArrayList<String> tables = new ArrayList<>();
            
            while(resultSet.next()) {
                
                String tableName = resultSet.getString("TABLE_NAME");
                
                tables.add(tableName);
            }
            
            while (true)
            {
                System.out.println("Enter the command (INSERT/SELECT)");
                
                String command = input.readLine();
                
                switch (command)
                {
                    
                    case INSERT:
                        
                        System.out.println("Tables:");
                        
                        for (String table: tables){
                            
                            System.out.println(table);
                        }
                        
                        System.out.println("select table name ");
                        
                        String tableName = input.readLine();
                        
                        ResultSet columns = connection.getMetaData().getColumns(null, null, tableName, null);
                        
                        ResultSetMetaData mDataColumn =columns.getMetaData();
                        
                        int columnCount = mDataColumn.getColumnCount();
                        
                        while (columns.next()) {
                            
                            for (int iterator =1; iterator<= columnCount; iterator++){
                                
                                String column  = mDataColumn.getColumnName(iterator);
                                
                                System.out.println(column);
                            }
                            
                        }
                        
                        break;
                    
                    case SELECT:
                        
                        break;
                    
                    default:
                }
                
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
