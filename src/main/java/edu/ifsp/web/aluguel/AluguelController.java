package edu.ifsp.web.aluguel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.web.Command;

@WebServlet("/aluguel/*")
public class AluguelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Map<String, Command> commandMap = new HashMap<>();
	
    public AluguelController() {
    	 commandMap.put("/alugar", new AluguelQuarto());
    	 commandMap.put("/listar", new AluguelListar());
    	 commandMap.put("/avaliar", new AluguelAvaliar());
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
        String path = request.getPathInfo();
        Command command = commandMap.get(path);
        
        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Comando não encontrado: " + path);
        }
    }

}
