package edu.ifsp.web.quarto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ifsp.web.Command;

@WebServlet("/quarto/*")
public class QuartoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Map<String, Command> commandMap = new HashMap<>();

    public QuartoController() {
        // Inicializa o mapa de comandos com todas as operações suportadas
        commandMap.put("/quarto/listar", new ListarQuartos());    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String path = request.getPathInfo(); // request.getPathInfo() para pegar o caminho relativo após o /quarto
        if (path == null) {
            path = "/";
        }
        Command command = commandMap.get("/quarto" + path); // Adiciona "/quarto" ao caminho

        if (command != null) {
            command.execute(request, response); // Executa o comando associado à requisição
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Comando não encontrado: " + path);
        }
    }
}
