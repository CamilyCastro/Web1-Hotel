package edu.ifsp.web.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.persistencia.ClienteDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.web.templates.Template;

@WebServlet("/cliente/cadastro")
public class ClienteCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        // Redireciona para home
	        Template.render("cliente/cadastro", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDAO dao = new ClienteDAO();

		// Recupera dados do formulário de cadastro
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Cria um objeto Cliente com os dados recebidos
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setPassword(password);

		// Insere o cliente no banco de dados
		try {
			dao.insertCliente(cliente);

			// Armazena informações na sessão(loga o cliente)
			int id = dao.validateCliente(email, password).getId();
			HttpSession session = request.getSession();
			session.setAttribute("id_logado", id);

			// Redireciona para home
			response.sendRedirect(request.getContextPath() + "/home");
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
