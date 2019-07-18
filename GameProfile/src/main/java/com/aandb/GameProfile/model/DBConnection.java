package com.aandb.GameProfile.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private Connection jdbcConnection;
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    
    public DBConnection(String jdbcURL, String jdbcUsername, String jdbcPassword)
    {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    
    public void connect() throws SQLException
    {
        if(jdbcConnection == null || jdbcConnection.isClosed())
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Cannot find Driver!");
                throw new SQLException(e);
            }
            
            try
            {
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            }
            catch(SQLException e)
            {
                System.out.println("Failed to Connect to Database!");
                e.printStackTrace();
            }
        }
    }
    
    public void disconnect() throws SQLException
    {
        if(jdbcConnection != null && !jdbcConnection.isClosed())
        {
            jdbcConnection.close();
        }
    }
    
    public Connection getJdbcConnection()
    {
        return jdbcConnection;
    }
}
