package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletListeProfils
 */
@WebServlet("/ListeProfils")
public class ServletListeProfils extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		try {
			listeUtilisateurs = UtilisateurManager.getInstance().allUtilisateurs();
			request.setAttribute("usersList", listeUtilisateurs);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		 this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/fr/listeProfils.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("utilisateur");
		try {
			Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(pseudo);
			 request.setAttribute("utilisateur", user);
		        request.getRequestDispatcher("/WEB-INF/jsp/fr/affichageProfil.jsp").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
