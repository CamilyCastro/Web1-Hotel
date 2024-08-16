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
	
	public List<Quarto> filtrar(String entrada, int capacidade) throws PersistenceException, ParseException {	
		List<Quarto> quartos = new ArrayList<Quarto>();
		
		try (Connection conn = DatabaseConnector.getConnection()) {	

			String query = "SELECT q.id, q.descricao, q.nota, q.capacidade, q.valor " +
                    "FROM quartos q " +
                    "LEFT JOIN aluguel a ON q.id = a.id_quarto " +
                    "WHERE (a.saida IS NULL OR a.saida < ?) " +
                    "AND q.capacidade = ?";

		     try (PreparedStatement ps = conn.prepareStatement(query)) {
		         ps.setString(1, entrada);
		         ps.setInt(2, capacidade);
		         
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
	
	public Quarto listarFavoritados(int idQuarto) throws PersistenceException, ParseException, SQLException {	
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
}

