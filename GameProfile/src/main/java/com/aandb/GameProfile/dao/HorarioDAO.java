package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Horario;

public class HorarioDAO {
	private DBConnection dbConnection;
    private Connection connection;

    public HorarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Horario horario) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM horario";
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
    	
    	
        String sql = "INSERT INTO horario VALUES (?, ?, ?, ?, ?, ? )";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setString(2, horario.getDia_semana());
        statement.setString(3, horario.getTipo_clase());
        statement.setString(4, horario.getGrupo());
        statement.setInt(5, horario.getId_silabo());
        statement.setString(6, horario.getHorario());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    /*
    public List<Teacher> listTeachers() throws SQLException
    {
        List<Teacher> listTeachers = new ArrayList<Teacher>();
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
            String grado_academico = resultSet.getString("grado_academico");
            String docentescol = resultSet.getString("docentescol");
            int id_departamento_academico = resultSet.getInt("iddepartamentoacademico");
            
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setDni(dni);
            teacher.setNombre(name);
            teacher.setGrado_academico(grado_academico);
            teacher.setDocentescol(docentescol);
            teacher.setId_departamento_academico(id_departamento_academico);
            
            listTeachers.add(teacher);
        }
        
        dbConnection.disconnect();
        return listTeachers;
    }
    
    public Teacher getTeacherByName(String name) throws SQLException
    {
        Teacher teacher = new Teacher();
        
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
        	teacher.setGrado_academico(res.getString("grado_academico"));
        	teacher.setDocentescol(res.getString("docentescol"));
        	teacher.setId_departamento_academico(res.getInt("iddepartamentoacademico"));
        }
        res.close();
        dbConnection.disconnect();
        
        return teacher;
    }*/
}
