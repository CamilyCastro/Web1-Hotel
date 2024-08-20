package edu.ifsp.web.quarto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Aluguel;
import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.AluguelDAO;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class ListarQuartos implements Command{
	
	public ListarQuartos() {
		
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuartosDAO dao = new QuartosDAO();
		AluguelDAO aluguelDAO = new AluguelDAO();
		List<Aluguel> alugueis;
		List<Quarto> quartos;
		
		//recupera dados da home
		int capacidade = Integer.parseInt(request.getParameter("capacidade"));
		String entrada = request.getParameter("entrada");
		String saida = request.getParameter("saida");
	
		HttpSession session = request.getSession();
        session.setAttribute("entrada", entrada);
        session.setAttribute("saida", saida);
        session.setAttribute("capacidade", capacidade);
		
		quartos = dao.filtrar(capacidade);
		alugueis = aluguelDAO.filtrarAluguel();
		
		List<Quarto> listaFinal = new ArrayList<>();
		listaFinal = getDisponiveis(quartos, alugueis, entrada, saida);
		request.setAttribute("quartos", listaFinal);
				
		//persistencia do formulario
		request.setAttribute("capacidade", capacidade);
		request.setAttribute("entrada", entrada);
		request.setAttribute("saida", saida);

		Template.render("quarto/listar", request, response);		
	}
	
	public List<Quarto> getDisponiveis(List<Quarto> quartos, List<Aluguel> alugueis, String entrada, String saida) throws ParseException {
	    List<Quarto> disponiveis = new ArrayList<>();

	    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    Date entradaChegando = formato.parse(entrada); 
	    Date saidaChegando = formato.parse(saida);

	    for (Quarto quarto : quartos) {
	        boolean isDisponivel = true;

	        for (Aluguel aluguel : alugueis) {
	            // Verifica se o aluguel é do mesmo quarto
	            if (quarto.getId() == aluguel.getIdQuarto()) {
	                Date entradaBanco = formato.parse(aluguel.getEntrada());
	                Date saidaBanco = formato.parse(aluguel.getSaida());

	                // Verifica se há colisão nas datas (sobreposição)
	                if (!(saidaChegando.before(entradaBanco) || entradaChegando.after(saidaBanco))) {
	                    isDisponivel = false;
	                    break; // Se houver colisão, podemos parar de verificar este quarto
	                }
	            }
	        }

	        // Se o quarto não teve colisão de datas, ele é adicionado à lista de disponíveis
	        if (isDisponivel) {
	            disponiveis.add(quarto);
	        }
	    }

	    return disponiveis;
	}
}

