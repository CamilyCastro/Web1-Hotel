package edu.ifsp.web.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.persistencia.ClienteDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class CadastrarCliente implements Command{
	
	public CadastrarCliente( ) {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		if((nome == null) || (email == null) || (password == null)) {
			
			Template.render("cliente/cadastro", request, response);
			
		}else {
			
			ClienteDAO dao = new ClienteDAO();	

			Cliente cliente = new Cliente();
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setPassword(password);

			try {
				dao.insertCliente(cliente);

				int id = dao.validateCliente(email, password).getId();	
				HttpSession session = request.getSession();
				session.setAttribute("id_logado", id);

				response.sendRedirect(request.getContextPath() + "/home");
			} catch (PersistenceException e) {
				e.printStackTrace();
			}			
		}
	}
}
