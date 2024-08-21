package edu.ifsp.web.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.web.Command;

public class LogoutCliente implements Command{
	
	public LogoutCliente() {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		response.sendRedirect("cliente/login");
	}
	
}
