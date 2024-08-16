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
        
        // Recuperando ou criando a lista de quartos favoritos
        List<Quarto> favoritos = (List<Quarto>) session.getAttribute("favoritos");
        if (favoritos == null) {
            favoritos = new ArrayList<>();
            session.setAttribute("favoritos", favoritos);
        }

        // Buscando o quarto que foi favoritado
        int quartoId = Integer.parseInt(request.getParameter("quarto"));
        QuartosDAO dao = new QuartosDAO();
        Quarto quarto = dao.listarFavoritados(quartoId);

        // Adicionando o quarto aos favoritos, se não estiver já adicionado
        if (!favoritos.contains(quarto)) {
            favoritos.add(quarto);
        }

        // Armazenando a mensagem de sucesso na sessão
        session.setAttribute("favoritoMsg", "Quarto favoritado com sucesso.");

        // Recuperando a data de entrada e a capacidade da sessão
        String entrada = (String) session.getAttribute("entrada");
        Integer capacidade = (Integer) session.getAttribute("capacidade");

        // Passando os parâmetros na URL para manter o contexto
        response.sendRedirect(request.getContextPath() + "/quarto/listar?entrada=" + entrada + "&capacidade=" + capacidade);
    }
}
