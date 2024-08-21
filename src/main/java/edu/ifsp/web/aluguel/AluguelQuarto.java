package edu.ifsp.web.aluguel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.persistencia.AluguelDAO;
import edu.ifsp.web.Command;

public class AluguelQuarto implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String entrada = (String) session.getAttribute("entrada");
		String saida = (String) session.getAttribute("saida");
		Integer capacidade = (Integer) session.getAttribute("capacidade");

		try {
			int id_logado = (Integer) session.getAttribute("id_logado");

			int quartoId = Integer.parseInt(request.getParameter("quarto"));

			AluguelDAO aluguel = new AluguelDAO();
			aluguel.insertAluguel(entrada, saida, quartoId, id_logado);

			session.setAttribute("favoritoMsg", "Quarto adicionado a lista de reservas.");
			response.sendRedirect(request.getContextPath() + "/quarto/listar?entrada=" + entrada + "&saida=" + saida + "&capacidade=" + capacidade);

		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/cliente/login");
		}
	}

}
