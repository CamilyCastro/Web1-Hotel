package edu.ifsp.persistencia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static String JDBC_URL = "jdbc:h2:~/hotelweb";;
    private static final String JDBC_USER = "sa";          
    private static final String JDBC_PASSWORD = "sa";      

   static {
        try {

        	executeSqlScript(getConnection(), "/schema.sql");
            executeSqlScript(getConnection(), "/data.sql");
                          
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao configurar o caminho do banco de dados.", e);
        }
    } 
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro ao carregar o driver do H2", e);
        }

        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        
        return conn;
    }


   private static void executeSqlScript(Connection conn, String scriptPath) {
    	
    	   InputStream inputStream = null;
    	   
           try {
               inputStream = DatabaseConnector.class.getResourceAsStream(scriptPath);
               if (inputStream == null) {
                   throw new IllegalArgumentException("Script SQL não encontrado: " + scriptPath);
               }

               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             
               StringBuilder sqlBuilder = new StringBuilder();
               String line;
               while ((line = reader.readLine()) != null) {
                   sqlBuilder.append(line).append("\n");
               }
               String sql = sqlBuilder.toString();
	
	            try (Statement stmt = conn.createStatement()) {
	                stmt.execute(sql); 
	            }
	            
	
	        } catch (IllegalArgumentException e) {
	            System.err.println(e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Erro ao executar o script SQL: " + scriptPath);
	        }
    }

}
