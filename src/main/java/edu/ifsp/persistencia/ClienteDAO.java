package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.ifsp.modelo.Cliente;

public class ClienteDAO {

    public void insertCliente(Cliente cliente) throws PersistenceException {
        String query = "INSERT INTO cliente (nome, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getPassword());

            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
    
    public Cliente validateCliente(String email, String password) throws PersistenceException {
        String query = "SELECT id, nome, email, password FROM cliente WHERE email = ? AND password = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            return null;
        }

        return cliente;
    }


    public Cliente getClienteById(int id) throws PersistenceException {
        String query = "SELECT id, nome, email, password FROM cliente WHERE id = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return cliente;
    }

    public void updateCliente(Cliente cliente) throws PersistenceException {
        String query = "UPDATE cliente SET nome = ?, email = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getPassword());
            ps.setInt(4, cliente.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new PersistenceException("Cliente não encontrado com o ID: " + cliente.getId());
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public void deleteCliente(int id) throws PersistenceException {
        String query = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new PersistenceException("Nenhum cliente encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
