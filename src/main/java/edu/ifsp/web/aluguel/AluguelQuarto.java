package edu.ifsp.web.aluguel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.AluguelDAO;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;

public class AluguelQuarto implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // id do quarto escolhido na tela listar quarto
        int quartoId = Integer.parseInt(request.getParameter("quarto"));
        
        //recuperando da sessão
    	HttpSession session = request.getSession();
		String entrada = (String) session.getAttribute("entrada");
		String saida   = (String) session.getAttribute("saida");
		Integer capacidade = (Integer) session.getAttribute("capacidade");
		
		AluguelDAO aluguel = new AluguelDAO();
		aluguel.insertAluguel(entrada, saida, quartoId, 1 /**ID ANA - TEMPORARIO*/ );
		
		//retorna para '/quarto/listar' utilizando os parametros da primeira busca
		response.sendRedirect(request.getContextPath() + "/quarto/listar?entrada=" + entrada + "&saida=" + saida + "&capacidade=" + capacidade);
	}

}
