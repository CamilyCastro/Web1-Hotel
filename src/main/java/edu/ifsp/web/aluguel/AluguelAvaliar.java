package edu.ifsp.web.aluguel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;

public class AluguelAvaliar implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		int quarto = Integer.parseInt(request.getParameter("quarto"));
		int nota = Integer.parseInt(request.getParameter("nota"));
		
		QuartosDAO quartos = new QuartosDAO();
		quartos.updateNota(nota, quarto);
		
		HttpSession session = request.getSession();
		session.setAttribute("alugadoMsg", "Quarto avaliado! Muito obrigado!");
		response.sendRedirect(request.getContextPath() + "/aluguel/listar");
	}
}
