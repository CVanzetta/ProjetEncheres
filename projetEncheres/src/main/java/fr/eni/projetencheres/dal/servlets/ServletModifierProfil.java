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
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//if (session != null) {
		    String utilisateurConnecte = (String) session.getAttribute("identifiant");

		    //if (utilisateurConnecte != null) {
		    	try {
		    		Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(utilisateurConnecte);
		    		request.setAttribute("user", user);
		    		request.getRequestDispatcher("/WEB-INF/jsp/fr/modifierProfil.jsp").forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
		    }
		//}
	//}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String utilisateurConnecte = (String) session.getAttribute("identifiant");
	    
		try {
	        Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(utilisateurConnecte);

	        user.setNom(request.getParameter("nom"));
	        user.setPrenom(request.getParameter("prenom"));
	        user.setEmail(request.getParameter("email"));
	        user.setTelephone(request.getParameter("tel"));
	        user.setRue(request.getParameter("rue"));
	        user.setCode_postal(request.getParameter("cp"));
	        user.setVille(request.getParameter("ville"));

	        UtilisateurManager.getInstance().modifierProfil(user);
	        
	        response.sendRedirect(request.getContextPath() + "/MonProfil");
	        //request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}

