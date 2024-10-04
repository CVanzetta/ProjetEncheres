package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.ArticleBLL;
import fr.eni.projetencheres.bll.CategorieBLL;
import fr.eni.projetencheres.bll.EnchereArticleBLL;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.EnchereArticle;

@WebServlet("/listeArticles")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBLL articleBLL;
	private EnchereArticleBLL enchereArticleBLL;
	private CategorieBLL categorieBLL;

	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
		categorieBLL = new CategorieBLL();
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categorie = request.getParameter("selectCategory");
		String textArticle = request.getParameter("textArticle");
		HttpSession session = request.getSession(false);

		List<EnchereArticle> listeEnchereArticle = new ArrayList<>();
		List<Categorie> listeCategorie = new ArrayList<>();
		
		/*
		try {
			if (session != null && session.getAttribute("userId") != null) {
				String mestrucs = request.getParameter("mestrucs");
				if ("mesachats".equals(mestrucs)) {
					if ("mesachatsouverts".equals(request.getParameter("mesachatsouverts"))) {
						listeEnchereArticle = enchereArticleBLL.selectJoin();
					} else if ("mesachatsencheres".equals(request.getParameter("mesachatsencheres"))) {
						listeEnchereArticle = enchereArticleBLL.selectJoinByUserEnchere(session.getAttribute("userId"));
						// Effectuez ici des opérations spécifiques pour les enchères de l'utilisateur
					} else if ("mesachatsencheresremporte".equals(request.getParameter("mesachatsencheresremporte"))) {
						listeEnchereArticle = enchereArticleBLL
								.selectJoinByUserEnchereVD(session.getAttribute("userId"));
						// Effectuez ici des opérations spécifiques pour les enchères remportées par
						// l'utilisateur
					}
				} else if ("mesventes".equals(mestrucs)) {
					List<EnchereArticle> listeEnchereArticleByUser = enchereArticleBLL
							.selectJoinByUser(session.getAttribute("userId"));
					if ("mesventesencours".equals(request.getParameter("mesventesencours"))) {
						listeEnchereArticle.addAll(listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("EC")).collect(Collectors.toList()));
					} else if ("mesventesnondebutees".equals(request.getParameter("mesventesnondebutees"))) {
						listeEnchereArticle.addAll(listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("CR")).collect(Collectors.toList()));
					} else if ("mesventesterminees".equals(request.getParameter("mesventesterminees"))) {
						listeEnchereArticle.addAll(listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("VD")).collect(Collectors.toList()));
					}
				}

				if (categorie != null || textArticle != null) {
					if (categorie == null || categorie.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());
					} else if (textArticle == null || textArticle.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
					} else {
						listeEnchereArticle = listeEnchereArticle.stream().filter(
								t -> t.getNom_article().contains(textArticle) && t.getCategorie().contains(categorie))
								.collect(Collectors.toList());
					}
				}
			} else {
				// L'utilisateur n'est pas connecté
				if (categorie != null || textArticle != null) {
					if (categorie == null || categorie.isEmpty()) {
						listeEnchereArticle = enchereArticleBLL.selectJoinLike(textArticle);
					} else if (textArticle == null || textArticle.isEmpty()) {
						listeEnchereArticle = enchereArticleBLL.selectJoinCat(categorie);
					} else {
						listeEnchereArticle = enchereArticleBLL.selectJoinCatLike(categorie, textArticle);
					}
				} else {
					listeEnchereArticle = enchereArticleBLL.selectJoin();
				}
			}
		} catch (BusinessException e) {
			// Gérer les exceptions BusinessException
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			return; // Terminer le traitement en cas d'erreur
		}*/

		try {
			listeCategorie = categorieBLL.selectAll();
		} catch (BusinessException e) {
			// Gérer l'exception, par exemple en enregistrant un message d'erreur
			e.printStackTrace(); // À adapter en fonction de votre gestion d'erreurs
		}

		request.setAttribute("listeEnchereArticle", listeEnchereArticle);
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("/WEB-INF/jsp/ListeEncheres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
