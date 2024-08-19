package edu.ifsp.web.quarto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Quarto;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class VerFavoritos implements Command {
	
	public VerFavoritos( ) {}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		// Recuperando a data de entrada e a capacidade da sessão
		String entrada = (String) session.getAttribute("entrada");
		String saida = (String) session.getAttribute("saida");
		Integer capacidade = (Integer) session.getAttribute("capacidade");
	     
		// Armazenando na requisição para ser usado no template, se necessário
		request.setAttribute("entrada", entrada);
		request.setAttribute("saida", saida);
		request.setAttribute("capacidade", capacidade);
				
		// Renderizando o template de favoritos
		Template.render("quarto/favoritos", request, response);
		
	}
}
