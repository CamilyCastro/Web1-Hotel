package edu.ifsp.web.quarto;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		int capacidade = Integer.parseInt(request.getParameter("capacidade"));
		
		quartos = dao.listar(request.getParameter("entrada"), capacidade);
		request.setAttribute("quartos", quartos);
		Template.render("quarto/listar", request, response);		
	}
}
