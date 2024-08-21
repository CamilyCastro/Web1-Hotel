package edu.ifsp.web.aluguel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.AluguelDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class AluguelListar implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
		String entrada = (String) session.getAttribute("entrada");
		String saida = (String) session.getAttribute("saida");
		Integer capacidade = (Integer) session.getAttribute("capacidade");
		Integer id_logado = (Integer) session.getAttribute("id_logado");
		
		if(id_logado != null) {
			AluguelDAO aluguel = new AluguelDAO();
			List alugueis = aluguel.getListaAlugueis(id_logado);
			request.setAttribute("alugueis", alugueis);
			
			request.setAttribute("capacidade", capacidade);
			request.setAttribute("entrada", entrada);
			request.setAttribute("saida", saida);
			
			Template.render("aluguel/listar", request, response);		
		}else {
			response.sendRedirect(request.getContextPath() + "/cliente/login");
		}

		

	}

}
