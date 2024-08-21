package edu.ifsp.web.quarto;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ifsp.modelo.Quarto;
import edu.ifsp.persistencia.QuartosDAO;
import edu.ifsp.web.Command;

public class FavoritarQuarto implements Command {

    public FavoritarQuarto() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
        List<Quarto> favoritos = (List<Quarto>) session.getAttribute("favoritos");
        if (favoritos == null) {
            favoritos = new ArrayList<>();
            session.setAttribute("favoritos", favoritos);
        }

        int quartoId = Integer.parseInt(request.getParameter("quarto"));
        QuartosDAO dao = new QuartosDAO();
        Quarto quarto = dao.getQuarto(quartoId);
        
        boolean encontrado = false;
        
        for (Quarto q : favoritos) {
            if (q.getId() == quarto.getId()) {
                encontrado = true;
                break; 
            }
        }

        if (encontrado) {
            session.setAttribute("favoritoMsg", "Quarto já está nos favoritos.");
        } else {
            favoritos.add(quarto);
            session.setAttribute("favoritoMsg", "Quarto favoritado com sucesso.");
        }

        String entrada = (String) session.getAttribute("entrada");
        String saida   = (String) session.getAttribute("saida");
        Integer capacidade = (Integer) session.getAttribute("capacidade");

		response.sendRedirect(request.getContextPath() + "/quarto/listar?entrada=" + entrada + "&saida=" + saida + "&capacidade=" + capacidade);
    }
}
