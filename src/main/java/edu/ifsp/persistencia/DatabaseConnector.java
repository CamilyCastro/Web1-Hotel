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

    private static String JDBC_URL;
    private static final String JDBC_USER = "sa";          // Usuário padrão do H2
    private static final String JDBC_PASSWORD = "sa";      // Senha padrão do H2

    static {
        try {
        	// Define o caminho absoluto manualmente para o banco de dados H2
        	 String dbPath = Paths.get("/hotelweb/src/main/resources").toAbsolutePath().toString();
        	 JDBC_URL = "jdbc:h2:file:" + dbPath;
             System.out.println("Banco de dados será salvo em: " + JDBC_URL);
             
             // Executa o script SQL para criar tabelas e inserir dados
             executeSqlScript(getConnection(), "/schema.sql");
             executeSqlScript(getConnection(), "/data.sql");
             
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao configurar o caminho do banco de dados.", e);
        }
    }

    // Método para obter a conexão
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver do H2
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro ao carregar o driver do H2", e);
        }

        // Retorna uma conexão ao banco de dados
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        
        return conn;
    }

    // Método para executar um script SQL
    private static void executeSqlScript(Connection conn, String scriptPath) {
    	
    	   InputStream inputStream = null;
    	   
           try {
               // Tenta carregar o arquivo SQL do classpath
               inputStream = DatabaseConnector.class.getResourceAsStream(scriptPath);
               if (inputStream == null) {
                   throw new IllegalArgumentException("Script SQL não encontrado: " + scriptPath);
               }

               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             
            // Ler o script SQL do arquivo
               StringBuilder sqlBuilder = new StringBuilder();
               String line;
               while ((line = reader.readLine()) != null) {
                   sqlBuilder.append(line).append("\n");
               }
               String sql = sqlBuilder.toString();
	
	            // Executar o script SQL
	            try (Statement stmt = conn.createStatement()) {
	                stmt.execute(sql); 
	            }
	            
	
	        } catch (IllegalArgumentException e) {
	            // Exceção para script SQL não encontrado
	            System.err.println(e.getMessage());
	        } catch (Exception e) {
	            // Outras exceções
	            e.printStackTrace();
	            System.err.println("Erro ao executar o script SQL: " + scriptPath);
	        }
    }

}
