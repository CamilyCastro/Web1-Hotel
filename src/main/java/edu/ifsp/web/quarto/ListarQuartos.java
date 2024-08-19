package edu.ifsp.web.quarto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class ListarQuartos implements Command{
	
	public ListarQuartos() {
		
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuartosDAO dao = new QuartosDAO();
		List<Quarto> quartos;
		
		//recupera dados da home
		int capacidade = Integer.parseInt(request.getParameter("capacidade"));
		String entrada = request.getParameter("entrada");
		String saida = request.getParameter("saida");
	
		HttpSession session = request.getSession();
        session.setAttribute("entrada", entrada);
        session.setAttribute("saida", saida);
        session.setAttribute("capacidade", capacidade);
		
		quartos = dao.filtrar(entrada, capacidade);
		request.setAttribute("quartos", quartos);
		
		//persistencia do formulario
		request.setAttribute("capacidade", capacidade);
		request.setAttribute("entrada", entrada);
		request.setAttribute("saida", saida);

		Template.render("quarto/listar", request, response);		
	}
}
