package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Estrategia_Enseniansa;

public class Estrategia_EnseniansaDAO {
	private DBConnection dbConnection;
    private Connection connection;

    public Estrategia_EnseniansaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Estrategia_Enseniansa estrategia_ensenianza) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM `estrategia-enseñanza`";
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
    	
        String sql = "INSERT INTO `estrategia-enseñanza` VALUES (?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setString(2, estrategia_ensenianza.getTipo());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Estrategia_Enseniansa> listEstrategias() throws SQLException
    {
        List<Estrategia_Enseniansa> listEstrategias = new ArrayList<Estrategia_Enseniansa>();
        String sql = "SELECT * FROM `estrategia-enseñanza`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String tipo = resultSet.getString("tipo");
            
            Estrategia_Enseniansa estrategia = new Estrategia_Enseniansa();
            estrategia.setId(id);
            estrategia.setTipo(tipo);
            
            listEstrategias.add(estrategia);
        }
        
        dbConnection.disconnect();
        return listEstrategias;
    }
    
    public Estrategia_Enseniansa getEstrategiaByType(String type) throws SQLException
    {
    	Estrategia_Enseniansa estrategia = new Estrategia_Enseniansa();
        
        String sql = "SELECT * FROM `estrategia-enseñanza` WHERE tipo='"+type+"';";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
        	estrategia.setId(res.getInt("id"));
        	estrategia.setTipo(res.getString("tipo"));
        }
        res.close();
        dbConnection.disconnect();
        
        return estrategia;
    }

}
