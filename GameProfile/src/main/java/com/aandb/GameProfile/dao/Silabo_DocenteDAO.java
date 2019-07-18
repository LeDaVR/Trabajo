package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Silabo_Docente;

public class Silabo_DocenteDAO {
	private DBConnection dbConnection;
    private Connection connection;

    public Silabo_DocenteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Silabo_Docente silabo_docente) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM `silabo-doc`";
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
    	
        String sql = "INSERT INTO `silabo-doc` VALUES (?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setInt(2, silabo_docente.getId_silabo());
        statement.setInt(3, silabo_docente.getId_docente());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Silabo_Docente> listSilabo_Docente() throws SQLException
    {
        List<Silabo_Docente> listSilabo_docente = new ArrayList<Silabo_Docente>();
        String sql = "SELECT * FROM `silabo-doc`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            int id_silabo = resultSet.getInt("silabo_id");
            int id_docente = resultSet.getInt("iddocente");
            
            Silabo_Docente silabo_docente = new Silabo_Docente();
            silabo_docente.setId(id);
            silabo_docente.setId_silabo(id_silabo);
            silabo_docente.setId_docente(id_docente);
            
            listSilabo_docente.add(silabo_docente);
        }
        
        dbConnection.disconnect();
        return listSilabo_docente;
    }
}
