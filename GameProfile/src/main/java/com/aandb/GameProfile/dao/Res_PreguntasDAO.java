package com.aandb.GameProfile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aandb.GameProfile.model.DBConnection;
import com.aandb.GameProfile.model.Res_Preguntas;

public class Res_PreguntasDAO {
	private DBConnection dbConnection;
    private Connection connection;

    public Res_PreguntasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException
    {
        dbConnection = new DBConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insert(Res_Preguntas res_pre) throws SQLException
    {
    	int nextId=0;
    	String sql_id = "SELECT * FROM `res-preguntas`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement_id = connection.createStatement();
        ResultSet resultSet = statement_id.executeQuery(sql_id);
        
        while(resultSet.next())
        {
            nextId = resultSet.getInt("id");
        }
        
    	String sql = "INSERT INTO `res-preguntas` VALUES (?, ?, ?, ?)";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nextId+1);
        statement.setInt(2, res_pre.getNivel());
        statement.setInt(3, res_pre.getId_resultados());
        statement.setInt(4, res_pre.getId_preguntas());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }
    
    public List<Res_Preguntas> listRes_Preguntas() throws SQLException
    {
        List<Res_Preguntas> listRes_Preguntas = new ArrayList<Res_Preguntas>();
        String sql = "SELECT * FROM `res-preguntas`";
        dbConnection.connect();
        connection = dbConnection.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            int nivel = resultSet.getInt("nivel");
            int id_resultados = resultSet.getInt("idresultados");
            int id_preguntas = resultSet.getInt("id_preguntas");
            
            Res_Preguntas res_pre = new Res_Preguntas();
            res_pre.setId(id);
            res_pre.setNivel(nivel);
            res_pre.setId_preguntas(id_preguntas);
            res_pre.setId_resultados(id_resultados);
            
            listRes_Preguntas.add(res_pre);
        }
        
        dbConnection.disconnect();
        return listRes_Preguntas;
    }
}
