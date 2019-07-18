package com.aandb.GameProfile.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.Cronograma;
import com.aandb.GameProfile.model.DBConnection;

public class CronogramaDAO {
	private DBConnection dbConnection;
    private Connection connection;
    
    public CronogramaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Cronograma cronograma) throws SQLException
    {
    	int nextId=0;
    	String sql = "SELECT * FROM cronograma";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
        String sqlinsert = "INSERT INTO cronograma VALUES (?,?,?,?,?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statementinsert = connection.prepareStatement(sqlinsert);
        statementinsert.setInt(1,nextId+1);
        statementinsert.setInt(2, cronograma.getId_silabo());
        statementinsert.setString(3, cronograma.getSemana());
        statementinsert.setString(4, cronograma.getTema_evaluacion());
        statementinsert.setInt(5, cronograma.getAvance());
        
        
        boolean rowInserted = statementinsert.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
}
