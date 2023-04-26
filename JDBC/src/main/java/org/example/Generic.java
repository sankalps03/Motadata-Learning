package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class Generic
{
    
    
    private static final String CREATE = "CREATE";
    
    private static final String UPDATE = "UPDATE";
    
    private static final String INSERT = "INSERT";
    
    private static final String DELETE = "DELETE";
    
    private static final String DROP = "DROP";
    
    private static final String ALTER = "ALTER";
    
    static  String jdbcURL = "jdbc:h2:~/test";
    
     static  String username = "sankalp";
    
     static String password = "sankalp";
    
    public static void main(String[] args)
    {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); Connection connection = DriverManager.getConnection(jdbcURL, username, password))
        {
            Generic object = new Generic();
            
            String query = object.makeQuery(input);
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.execute();
            
        
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public String makeQuery(BufferedReader input)
    {
        
        StringBuilder query = null;
        try
        {
            System.out.println("Enter the command (CREATE/UPDATE/INSERT/DELETE/DROP/ALTER)");
            
            String command = input.readLine();
            
            query = new StringBuilder();
            
            switch (command)
            {
                case CREATE:
                    
                    query = create(input);
                    
                    break;
                
                case UPDATE:
                    
                    query = update(input);
                    
                    break;
                
                case INSERT:
                    
                    query = insert(input);
                    
                    break;
                
                case DELETE:
                    
                    query = delete(input);
                    
                    break;
                
                case DROP:
                    
                    query = drop(input);
                    
                    break;
                
                case ALTER:
                    
                    query = alter(input);
                    
                    break;
                
                default:
                    
                    System.out.println("Wrong input");
                    
                    makeQuery(input);
                
            }
            
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
        return String.valueOf(query);
    }
    
    public StringBuilder create(BufferedReader input) throws IOException
    {
        
        StringBuilder query =new StringBuilder("CREATE TABLE ");
        
        System.out.println("Enter table name");
        
        String tableName = input.readLine();
        
        query.append(tableName+ " (");
        
        System.out.println("Enter number of columns");
        
        int noOfColumns = Integer.parseInt(input.readLine());
        
        int count = 0;
        
        HashSet<String> cName = new HashSet<>();
        
        while (count < noOfColumns){
            
            System.out.println("enter column name");
            
            String columnName = input.readLine();
            
            if (!cName.contains(columnName) && columnName != null){
                
                cName.add(columnName);
                
                query.append(columnName+ " ");
                
                System.out.println("enter data type for " + columnName +"(VARCHAR / INTEGER / FLOAT)");
                
                String dataType = input.readLine();
                
                if (dataType.equals("INTEGER") || dataType.equals("FLOAT")){
                    
                    if(count + 1 == noOfColumns){
                        
                        query.append(dataType);
                        
                    }else {
                    
                    query.append(dataType+", ");
                    }
                    
                    count++;
                    
                } else if (dataType.equals("VARCHAR")){
                    
                    if(count + 1 == noOfColumns){
                        
                        query.append(dataType);
                        
                    }else {
                    
                    query.append(dataType+"(255), ");
                    
                    }
                    
                    count++;
                    
                }
                else {
                    
                    System.out.println("Enter correct datatype");
                }
                
            }
            else {
                
                System.out.println("column name already exist please enter again ");
                
            }
            
            
            
            
        }
        
        query.append(" );");
        
        
        
        return query;
    }
    
    public StringBuilder update(BufferedReader input){
        
        StringBuilder query = new StringBuilder("Update");
        
        return query;
    
    }
    
    public StringBuilder insert(BufferedReader input){
        
        StringBuilder query = new StringBuilder("insert");
        
        return query;
    
        
    }
    
    public StringBuilder delete(BufferedReader input){
        
        StringBuilder query = new StringBuilder("delete");
        
        return query;
        
    }
    
    public StringBuilder drop(BufferedReader input){
        
        StringBuilder query = new StringBuilder("drop");
        
        return query;
        
    }
    
    public StringBuilder alter(BufferedReader input){
        
        StringBuilder query = new StringBuilder("Alter");
        
        return query;
        
    }
    
}
