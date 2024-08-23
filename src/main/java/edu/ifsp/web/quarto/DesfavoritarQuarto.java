package edu.ifsp.web.quarto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;

public class DesfavoritarQuarto implements Command{
	public DesfavoritarQuarto () {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        
	     List<Quarto> favoritos = (List<Quarto>) session.getAttribute("favoritos");
	     
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

	        String mensagem = null;
	        
	        if (encontrado) {
	        	session.setAttribute("favoritos", favoritos);
	        	mensagem = "Quarto removido da lista de favoritos.";
	        } else {
	        	mensagem = "Quarto não encontrado na lista de favoritos";
	        }

	        response.setContentType("text/plain");
	        response.getWriter().write(mensagem);
	        System.out.println("Mensagem enviada: " + mensagem);	   
	}
}
