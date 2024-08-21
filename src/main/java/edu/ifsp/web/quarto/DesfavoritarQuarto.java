package edu.ifsp.web.quarto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class DesfavoritarQuarto implements Command{
	public DesfavoritarQuarto () {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        
	     List<Quarto> favoritos = (List<Quarto>) session.getAttribute("favoritos");
	     
	     	// Buscando o quarto que foi desfavoritado
	        int quartoId = Integer.parseInt(request.getParameter("quarto"));
	        QuartosDAO dao = new QuartosDAO();
	        Quarto quarto = dao.getQuarto(quartoId);
	        
	        boolean encontrado = false;
	        
	        for(int i = 0; i < favoritos.size(); i++) {
	        	Quarto q = favoritos.get(i);
	        	
	        	if(q.getId() == quarto.getId()) {
	        		favoritos.remove(q);
	        		encontrado = true;
	        	}
	        	break;
	        }

	        if (encontrado) {
	        	session.setAttribute("favoritos", favoritos);
	            session.setAttribute("desfavoritoMsg", "Quarto removido da lista de favoritos.");
	        } else {
	            session.setAttribute("desfavoritoMsg", "Quarto não encontrado na lista de favoritos");
	        }

	        // Recuperando a data de entrada e a capacidade da sessão
	        String entrada = (String) session.getAttribute("entrada");
	        String saida   = (String) session.getAttribute("saida");
	        Integer capacidade = (Integer) session.getAttribute("capacidade");
	        
	     // Passando os parâmetros na URL para manter o contexto
	        response.sendRedirect(request.getContextPath() + "/quarto/favoritos?entrada=" + entrada + "&saida=" + saida + "&capacidade=" + capacidade);
		
	}
	
	
}
