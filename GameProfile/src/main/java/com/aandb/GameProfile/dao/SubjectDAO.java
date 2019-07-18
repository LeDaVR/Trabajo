package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Subject;

public class SubjectDAO
{
    private DBConnection dbConnection;
    private Connection connection;
    
    public SubjectDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Subject subject) throws SQLException
    {
        String sql = "INSERT INTO asignatura VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subject.getCasi());
        statement.setString(2, subject.getName());
        statement.setInt(3, subject.getSemester());
        statement.setString(4, subject.getCharacteristics());
        statement.setInt(5, subject.getDuration());
        statement.setInt(6, subject.getTheoricHours());
        statement.setInt(7, subject.getPracticHours());
        statement.setInt(8, subject.getTheoricPracticHours());
        statement.setInt(9, subject.getLaboratoryHours());
        statement.setInt(10, subject.getCredits());
        statement.setString(11, subject.getFoundation());
        statement.setString(12, subject.getSummary());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Subject> listSubjects() throws SQLException
    {
        List<Subject> listSubjects = new ArrayList<Subject>();
        String sql = "SELECT * FROM asignatura";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {   
            int casi = resultSet.getInt("casi");
            String name = resultSet.getString("nombre");
            int semester = resultSet.getInt("semestre");
            String characteristics = resultSet.getString("caracteristicas");;
            int duration = resultSet.getInt("duracion");
            int theoricHours = resultSet.getInt("horas_teoricas");
            int practicHours = resultSet.getInt("horas_practicas");
            int theoricPracticHours = resultSet.getInt("horas_teorico_practicas");
            int laboratoryHours = resultSet.getInt("horas_laboratorio");
            int credits = resultSet.getInt("creditos");
            String foundation = resultSet.getString("fundamentacion");
            String summary = resultSet.getString("sumilla");
            
            Subject subject = new Subject(casi, name, semester, characteristics, duration,
                                          theoricHours, practicHours, theoricPracticHours,
                                          laboratoryHours, credits, foundation, summary);
            
            listSubjects.add(subject);
        }
        
        dbConnection.disconnect();
        return listSubjects;
    }
    
    public Subject getSubjectByCasi(int casi) throws SQLException
    {
        Subject subject = new Subject();
        
        String sql = "SELECT * FROM asignatura WHERE casi=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, casi);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
            subject.setCasi(res.getInt("casi"));
            subject.setName(res.getString("nombre"));
            subject.setSemester(res.getInt("semestre"));
            subject.setCharacteristics(res.getString("caracteristicas"));
            subject.setDuration(res.getInt("duracion"));
            subject.setTheoricHours(res.getInt("horas_teoricas"));
            subject.setPracticHours(res.getInt("horas_practicas"));
            subject.setTheoricPracticHours(res.getInt("horas_teorico_practicas"));
            subject.setLaboratoryHours(res.getInt("horas_laboratorio"));
            subject.setCredits(res.getInt("creditos"));
            subject.setFoundation(res.getString("fundamentacion"));
            subject.setSummary(res.getString("sumilla"));
        }
        res.close();
        dbConnection.disconnect();
        
        return subject;
    }
    
    public Subject getSubjectbyName(String name) throws SQLException
    {
        Subject subject = new Subject();
        
        String sql = "SELECT * FROM asignatura WHERE nombre='"+name+"';";
        System.out.println(sql);
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        
        System.out.println(sql);
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
            subject.setCasi(res.getInt("casi"));
            subject.setName(res.getString("nombre"));
            subject.setSemester(res.getInt("semestre"));
            subject.setCharacteristics(res.getString("caracteristicas"));
            subject.setDuration(res.getInt("duracion"));
            subject.setTheoricHours(res.getInt("horas_teoricas"));
            subject.setPracticHours(res.getInt("horas_practicas"));
            subject.setTheoricPracticHours(res.getInt("horas_teorico_practicas"));
            subject.setLaboratoryHours(res.getInt("horas_laboratorio"));
            subject.setCredits(res.getInt("creditos"));
            subject.setFoundation(res.getString("fundamentacion"));
            subject.setSummary(res.getString("sumilla"));
        }
        res.close();
        dbConnection.disconnect();
        
        return subject;
    }
    
    public boolean update(Subject subject) throws SQLException
    {
        String sql = "UPDATE asignatura SET nombre=?, semestre=?, caracteristicas=?, "
                   + "duracion=?, horas_teoricas=?, horas_practicas=?, horas_teorico_practicas=?, "
                   + "horas_laboratorio=?, creditos=?, fundamentacion=?, sumilla=? WHERE casi=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, subject.getName());
        statement.setInt(2, subject.getSemester());
        statement.setString(3, subject.getCharacteristics());
        statement.setInt(4, subject.getDuration());
        statement.setInt(5, subject.getTheoricHours());
        statement.setInt(6, subject.getPracticHours());
        statement.setInt(7, subject.getTheoricPracticHours());
        statement.setInt(8, subject.getLaboratoryHours());
        statement.setInt(9, subject.getCredits());
        statement.setString(10, subject.getFoundation());
        statement.setString(11, subject.getSummary());
        statement.setInt(12, subject.getCasi());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }
    
    public boolean delete(Subject subject) throws SQLException
    {
        String sql = "DELETE FROM asignatura WHERE casi=?";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subject.getCasi());
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowDeleted;
    }
}
