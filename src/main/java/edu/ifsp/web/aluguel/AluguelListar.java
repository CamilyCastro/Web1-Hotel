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
		//recuperando da sessão
    	HttpSession session = request.getSession();
		String entrada = (String) session.getAttribute("entrada");
		String saida = (String) session.getAttribute("saida");
		Integer capacidade = (Integer) session.getAttribute("capacidade");
		/**String idCliente = (String) session.getAttribute("idCliente");*/

		AluguelDAO aluguel = new AluguelDAO();
		List alugueis = aluguel.getListaAlugueis(1 /**ID ANA - TEMPORARIO*/ );
		request.setAttribute("alugueis", alugueis);
		
		//persistencia do formulario
		request.setAttribute("capacidade", capacidade);
		request.setAttribute("entrada", entrada);
		request.setAttribute("saida", saida);
		
		Template.render("aluguel/listar", request, response);		

	}

}
