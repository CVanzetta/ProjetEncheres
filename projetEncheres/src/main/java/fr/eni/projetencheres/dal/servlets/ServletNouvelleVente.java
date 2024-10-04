package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.RetraitDAO;
import fr.eni.projetencheres.dal.UtilisateurDAO;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.*;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String utilisateurConnecte = (String) session.getAttribute("identifiant");
		try {
			Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(utilisateurConnecte);
			request.setAttribute("user", user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/fr/nouvelleVente.jsp").forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomArticle = request.getParameter("nomarticle");
	    String description = request.getParameter("description");
	    Integer noCategorie = Integer.parseInt(request.getParameter("categorie"));
	    Integer miseAPrix = Integer.parseInt(request.getParameter("miseaprix"));
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("datedebut"));
	    LocalDate dateFin = LocalDate.parse(request.getParameter("datefin"));
	   
	    // récupération utilisateurId de l'utilisateur connecté
	 	HttpSession session = request.getSession();
	 	String utilisateurConnecte = (String) session.getAttribute("identifiant");
	 	int ID = UtilisateurManager.getInstance().getID(utilisateurConnecte);
	    
		// validarion champs pour ajout d'un nouvel article
		List<String> erreurs = new ArrayList<>();

		if (nomArticle.isEmpty()) {
		    erreurs.add("Le nom de l'article est requis.");
		}

		if (description.isEmpty()) {
		    erreurs.add("La description est requise.");
		} else if (description.length() > 300) {
		    erreurs.add("La description ne doit pas dépasser 300 caractères.");
		}

		if (dateDebut.isBefore(LocalDate.now())) {
		    erreurs.add("La date de début de l'enchère ne peut pas être antérieure à la date d'aujourd'hui.");
		}

		if (dateFin.isBefore(dateDebut)) {
		    erreurs.add("La date de fin de l'enchère ne peut pas être antérieure à la date de début.");
		}

		if (!erreurs.isEmpty()) {
		    request.setAttribute("erreurs", erreurs);
		    request.getRequestDispatcher("WEB-INF/jsp/fr/nouvelleVente.jsp").forward(request, response);
		    return; // Ajoutez cette ligne pour empêcher l'exécution du code suivant
		}
		
		// création du nouvel article
		Article article = new Article(nomArticle, description, dateDebut, dateFin, miseAPrix, 0, ID, noCategorie);
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		
		try {
			// insertion de l'article
			articleDAO.insertArticle(article, ID);

			// récupération de la rue, du code postal et la ville pour le retrait
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");

			// si aucune adresse n'a été saisie dans le formulaire, récupération de l'adresse de l'utilisateur connecté
			UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
			String[] adresseUtilisateur;
			try {
				adresseUtilisateur = utilisateurDAO.getAdresseUtilisateur(utilisateurConnecte);
				if (rue == null || rue.isEmpty() || codePostal == null || codePostal.isEmpty() || ville == null
						|| ville.isEmpty()) {
					rue = adresseUtilisateur[0];
					codePostal = adresseUtilisateur[1];
					ville = adresseUtilisateur[2];
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Une erreur s'est produite lors de la récupération de l'adresse.");
			}
			
		     // création des modalités de retrait de l'article
			int lastNoArticle = articleDAO.getLastNoArticle(ID);
		    // System.out.println("Le noArticle récupéré est" +lastNoArticle);
		     Retrait retrait = new Retrait(lastNoArticle, rue, codePostal, ville);
		     RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		     try {
		    	    retraitDAO.insertRetrait(retrait);
		    	    request.getSession().setAttribute("insertionReussie", true);
				    response.sendRedirect(request.getContextPath() + "/encheres");
		    	} catch (BusinessException e) {
		    	    e.printStackTrace();
		    	    request.setAttribute("listeCodesErreur", "L'ajout d'un nouvel article a échoué. Veuillez réessayer.");
				    request.getRequestDispatcher("WEB-INF/jsp/fr/nouvelleVente.jsp").forward(request, response);
		    	}

		} catch (BusinessException e) {
		     e.printStackTrace();
		     request.setAttribute("listeCodesErreur", "L'ajout d'un nouvel article a échoué. Veuillez réessayer.");
		     request.getRequestDispatcher("WEB-INF/jsp/fr/nouvelleVente.jsp").forward(request, response);
		    }
		}

}