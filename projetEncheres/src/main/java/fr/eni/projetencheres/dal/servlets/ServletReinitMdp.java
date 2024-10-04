package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

 
/**
 * demande reset de mot de passe
 *
 */
@WebServlet("/ReinitMdp")
public class ServletReinitMdp extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ServletReinitMdp() {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/jsp/fr/reinitMdp.jsp").forward(request, response);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recipient = request.getParameter("email");
        String subject = "Votre mot de passe a été réinitialisé";
 
        }
}
