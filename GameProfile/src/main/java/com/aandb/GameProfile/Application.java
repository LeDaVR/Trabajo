package com.aandb.GameProfile;

import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.aandb.GameProfile.dao.BibliographyDAO;
import com.aandb.GameProfile.dao.Competencia_ResultadosDAO;
import com.aandb.GameProfile.dao.CompetenciasDAO;
import com.aandb.GameProfile.dao.CronogramaDAO;
import com.aandb.GameProfile.dao.Departamento_AcademicoDAO;
import com.aandb.GameProfile.dao.Estrategia_EnseniansaDAO;
import com.aandb.GameProfile.dao.EvaluacionDAO;
import com.aandb.GameProfile.dao.HorarioDAO;
import com.aandb.GameProfile.dao.PreguntaDAO;
import com.aandb.GameProfile.dao.Res_PreguntasDAO;
import com.aandb.GameProfile.dao.ResultadosDAO;
import com.aandb.GameProfile.dao.Silabo_BibliografiaDAO;
import com.aandb.GameProfile.dao.Silabo_DocenteDAO;
import com.aandb.GameProfile.dao.Silabo_EstrategiaDAO;
import com.aandb.GameProfile.dao.SilabusDAO;
import com.aandb.GameProfile.dao.UserDAO;
import com.aandb.GameProfile.dao.SubjectDAO;
import com.aandb.GameProfile.dao.GameDAO;

@SpringBootApplication
public class Application
{
    private static ConfigurableApplicationContext context;
    public static SilabusDAO silaboDAO;
    public static UserDAO userDAO;
    public static SubjectDAO subjectDAO;
    public static CronogramaDAO cronogramaDAO;
    public static ResultadosDAO resultadosDAO;
    public static GameDAO teacherDAO;
    public static Departamento_AcademicoDAO departamento_academicoDAO;
    public static Silabo_DocenteDAO silabo_docenteDAO;
    public static HorarioDAO horarioDAO;
    public static Estrategia_EnseniansaDAO estrategia_enseniansaDAO;
    public static Silabo_EstrategiaDAO silabo_estrategiaDAO;
    public static Silabo_BibliografiaDAO silabo_bibliografiaDAO;
    public static EvaluacionDAO evaluacionDAO;
    public static PreguntaDAO preguntaDAO;
    public static Res_PreguntasDAO res_preguntasDAO;
    
    public static void main(String[] args)
    {
        String jdbcURL = "jdbc:mysql://localhost:3306/academicSystemDB";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        
        try
        {
        	silaboDAO = new SilabusDAO(jdbcURL, jdbcUsername, jdbcPassword);
            userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
            subjectDAO = new SubjectDAO(jdbcURL, jdbcUsername, jdbcPassword);
            cronogramaDAO = new CronogramaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            resultadosDAO = new ResultadosDAO(jdbcURL, jdbcUsername, jdbcPassword);
            teacherDAO  = new GameDAO(jdbcURL, jdbcUsername, jdbcPassword);
            departamento_academicoDAO = new Departamento_AcademicoDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_docenteDAO = new Silabo_DocenteDAO(jdbcURL, jdbcUsername, jdbcPassword);
            horarioDAO = new HorarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
            estrategia_enseniansaDAO = new Estrategia_EnseniansaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_estrategiaDAO = new Silabo_EstrategiaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            silabo_bibliografiaDAO = new Silabo_BibliografiaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            evaluacionDAO = new EvaluacionDAO(jdbcURL, jdbcUsername, jdbcPassword);
            preguntaDAO = new PreguntaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            res_preguntasDAO = new Res_PreguntasDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } 
        catch(SQLException e)
        {
            System.out.println("Failed to initialize the DAOs!");
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("CONNECTION SUCCESS! Done At: " + jdbcURL);
        System.out.println("--------------------------------------------------------------------------");
        context = SpringApplication.run(Application.class, args);
    }
    
    @PreDestroy
    public void onShutDown()
    {
        context.close();
        System.out.println("Closing safety . . .");
    }
}
