package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;


@WebServlet("/SupprimerProfil")
public class ServletSupprimerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String utilisateurConnecte = (String) session.getAttribute("identifiant");
	    
		try {
	       UtilisateurManager.getInstance().supprimerUtilisateur(utilisateurConnecte);
	       
	    // Déconnecter l'utilisateur après suppression
	       session.invalidate();
	        
	       request.getRequestDispatcher("WEB-INF/jsp/fr/accueil.jsp").forward(request, response);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
