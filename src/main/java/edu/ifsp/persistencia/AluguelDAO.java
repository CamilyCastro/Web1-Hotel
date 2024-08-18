package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.modelo.Aluguel;


public class AluguelDAO {
	
	public void insertAluguel(String entrada, String saida, int idQuarto, int idCliente) throws PersistenceException, ParseException, SQLException {

	    try (Connection conn = DatabaseConnector.getConnection()) {

	        // Inserir dados na tabela aluguel
	        String insertQuery = "INSERT INTO aluguel (entrada, saida, id_quarto, id_cliente) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
	            insertPs.setString(1, entrada);
	            insertPs.setString(2, saida);
	            insertPs.setInt   (3, idQuarto);
	            insertPs.setInt   (4, idCliente);

	            insertPs.executeUpdate();
	            conn.commit();
//	            if (rowsAffected > 0) {
//	                System.out.println("Aluguel registrado com sucesso.");
//	            } else {
//	                throw new SQLException("Falha ao registrar o aluguel.");
//	            }
	        }
	    } catch (SQLException e) {
	        throw new PersistenceException(e);
	    }
	}
	
	
	public List<Aluguel> getListaAlugueis(int idCliente) throws PersistenceException, ParseException {    
	    List<Aluguel> alugueis = new ArrayList<>();

	    try (Connection conn = DatabaseConnector.getConnection()) {    
	        String query = "SELECT entrada, saida, id_quarto, id_cliente " +
	                       "FROM aluguel " +
	                       "WHERE id_cliente = ?";

	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, idCliente);
	            
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Aluguel aluguel = new Aluguel();
	                    aluguel.setEntrada(rs.getString("entrada"));
	                    aluguel.setSaida(rs.getString("saida"));
	                    aluguel.setIdQuarto(rs.getInt("id_quarto"));
	                    aluguel.setIdCliente(rs.getInt("id_cliente"));

	                    alugueis.add(aluguel);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new PersistenceException(e);            
	    }

	    return alugueis;
	}
	
}

