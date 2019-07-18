package com.aandb.GameProfile.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Game;


public class GameDAO {
	private DBConnection dbConnection;
    private Connection connection;

    public GameDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Game teacher) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM docentes";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
    	
        statement_id.close();
        dbConnection.disconnect();
    	
        String sql = "INSERT INTO docentes VALUES (?, ?, ?, ?, ?, ? , ? )";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setInt(2, teacher.getDni());
        statement.setString(3, teacher.getNombre());
        statement.setString(4, teacher.getGrado_academico());
        statement.setInt(5, teacher.getId_departamento_academico());
        statement.setString(6, teacher.getApellido_materno());
        statement.setString(7, teacher.getApellido_paterno());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Game> listTeachers() throws SQLException
    {
        List<Game> listTeachers = new ArrayList<Game>();
        String sql = "SELECT * FROM docentes";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            int dni = resultSet.getInt("dni");
            String name = resultSet.getString("nombre");
            String ap_paterno= resultSet.getString("apellido_paterno");
            String ap_materno= resultSet.getString("apellido_materno");
            String grado_academico = resultSet.getString("grado_academico");
            int id_departamento_academico = resultSet.getInt("iddepartamentoacademico");
            
            Game teacher = new Game();
            teacher.setId(id);
            teacher.setDni(dni);
            teacher.setNombre(name);
            teacher.setGrado_academico(grado_academico);
            teacher.setId_departamento_academico(id_departamento_academico);
            teacher.setApellido_materno(ap_materno);
            teacher.setApellido_paterno(ap_paterno);
            
            listTeachers.add(teacher);
        }
        
        dbConnection.disconnect();
        return listTeachers;
    }
    
    public Game getTeacherByName(String name) throws SQLException
    {
        Game teacher = new Game();
        
        String sql = "SELECT * FROM docentes WHERE nombre='"+name+"';";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	teacher.setId(res.getInt("id"));
        	teacher.setDni(res.getInt("dni"));
        	teacher.setNombre(res.getString("nombre"));
        	teacher.setApellido_materno("apellido_materno");
        	teacher.setApellido_paterno("apellido_paterno");
        	teacher.setGrado_academico(res.getString("grado_academico"));
        	teacher.setId_departamento_academico(res.getInt("iddepartamentoacademico"));
        }
        res.close();
        dbConnection.disconnect();
        
        return teacher;
    }
    /*
    public boolean update(Student student) throws SQLException
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
    
    public boolean delete(Student student) throws SQLException
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
    }
     */
}
