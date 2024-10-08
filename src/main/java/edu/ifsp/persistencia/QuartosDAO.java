package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import edu.ifsp.modelo.Quarto;

public class QuartosDAO {
	
	public List<Quarto> filtrar(int capacidade) throws PersistenceException, ParseException {	
		List<Quarto> quartos = new ArrayList<Quarto>();
		
		try (Connection conn = DatabaseConnector.getConnection()) {	

			String query = "SELECT id, descricao, nota, capacidade, valor " +
                    "FROM quartos " +
                    "WHERE capacidade = ?";

		     try (PreparedStatement ps = conn.prepareStatement(query)) {
		         ps.setInt(1, capacidade);
		         
		         try (ResultSet rs = ps.executeQuery()) {
		             while (rs.next()) {
		                 Quarto quarto = new Quarto();
		                 quarto.setId(rs.getInt("id"));
		                 quarto.setDescricao(rs.getString("descricao"));
		                 quarto.setNota(rs.getInt("nota"));
		                 quarto.setCapacidade(rs.getInt("capacidade"));
		                 quarto.setValor(rs.getDouble("valor"));
		
		                 quartos.add(quarto);
		             }
		         }
		     }
		} catch (SQLException e) {
			throw new PersistenceException(e);			
		}

		return quartos;
	}

	
	public Quarto getQuarto(int idQuarto) throws PersistenceException, ParseException, SQLException {	
		Quarto quarto = new Quarto();
		try (Connection conn = DatabaseConnector.getConnection()) {	
			
			String query = "SELECT id, descricao, nota, capacidade, valor FROM quartos WHERE id = ?";
                    
		     try (PreparedStatement ps = conn.prepareStatement(query)) {
		         ps.setInt(1, idQuarto);
		              
		         try (ResultSet rs = ps.executeQuery()) {
		             while (rs.next()) {
		                 quarto.setId(rs.getInt("id"));
		                 quarto.setDescricao(rs.getString("descricao"));
		                 quarto.setNota(rs.getInt("nota"));
		                 quarto.setCapacidade(rs.getInt("capacidade"));
		                 quarto.setValor(rs.getDouble("valor"));
		             }
				} catch (SQLException e) {
					throw new PersistenceException(e);			
				}
		
		         return quarto;
		     }
		}
	}
	

	public void updateNota(int nota, int idQuarto) throws PersistenceException, SQLException {
	        String query = "UPDATE quartos SET nota = ? WHERE id = ?";

	        try (Connection conn = DatabaseConnector.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {

	            ps.setInt(1, nota);
	            ps.setInt(2, idQuarto);

	            int affectedRows = ps.executeUpdate();
	            if (affectedRows == 0) {
	                throw new PersistenceException("Quarto encontrado com o ID: " + idQuarto);
	            }

	        } catch (SQLException e) {
	        	throw new PersistenceException(e);	
	        }
	        
	}
}

