package edu.ifsp.web.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.persistencia.ClienteDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class LoginCliente implements Command{
	
	public LoginCliente() {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ClienteDAO dao = new ClienteDAO();

	    // Recupera dados do formulário de login
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    if (email == null || password == null) {
	        // Primeira vez acessando a página de login, sem tentativa de login
	        request.setAttribute("msgErro", "");
	        Template.render("cliente/login", request, response);
	    } else {
	        try {
	            Cliente cliente = dao.validateCliente(email, password);

	            if (cliente != null) {
	                HttpSession session = request.getSession();
	                session.setAttribute("id_logado", cliente.getId());

	                // Redireciona para home
	                response.sendRedirect(request.getContextPath() + "/home");
	            } else {
	                request.setAttribute("msgErro", "Email e/ou senha incorretos !!");
	                Template.render("cliente/login", request, response);
	            }

	        } catch (PersistenceException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
