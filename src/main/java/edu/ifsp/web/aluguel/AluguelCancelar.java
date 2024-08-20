package edu.ifsp.web.aluguel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.persistencia.AluguelDAO;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;

public class AluguelCancelar implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idQuarto = Integer.parseInt(request.getParameter("bt-cancelar"));
		AluguelDAO aluguel = new AluguelDAO();
		aluguel.deleteReserva(idQuarto);
		response.sendRedirect(request.getContextPath() + "/aluguel/listar");
	}
}
