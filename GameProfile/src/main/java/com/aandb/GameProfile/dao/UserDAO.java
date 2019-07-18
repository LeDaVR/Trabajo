package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.User;

public class UserDAO
{
    private DBConnection dbConnection;
    private Connection connection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(User user) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM user";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        
        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,nextId+1);
        statement.setString(2, user.getName());
        statement.setString(3, user.getUsername());
        statement.setInt(4, user.getAge());
        statement.setString(5, user.getPassword());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<User> listUsers() throws SQLException
    {
        List<User> listUsers = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String user_name = resultSet.getString("user_name");
            int age = resultSet.getInt("age");
            String password = resultSet.getString("password");
            
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setUsername(user_name);
            user.setAge(age);
            user.setPassword(password);
            
            listUsers.add(user);
        }
        
        dbConnection.disconnect();
        return listUsers;
    }
    
    /*public User getStudentByCui(int cui) throws SQLException
    {
        User student = new User();
        
        String sql = "SELECT * FROM user WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cui);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
            student.id(res.getInt("cui"));
            student.setName(res.getString("nombre"));
            student.setLastName(res.getString("apellidos"));
            student.setAge(res.getInt("edad"));
            student.setSex(res.getString("sexo"));
        }
        res.close();
        dbConnection.disconnect();
        
        return student;
    }
    
    public boolean update(User student) throws SQLException
    {
        String sql = "UPDATE alumno SET nombre=?, apellidos=?, edad=?, sexo=? WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getName());
        statement.setString(2, student.getLastName());
        statement.setInt(3, student.getAge());       
        statement.setString(4, student.getSex());
        statement.setInt(5, student.getCui());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }
    
    public boolean delete(User student) throws SQLException
    {
        String sql = "DELETE FROM alumno WHERE cui=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getCui());
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowDeleted;
    }*/
}
