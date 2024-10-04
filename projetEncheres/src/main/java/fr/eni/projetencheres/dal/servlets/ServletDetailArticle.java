package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticleBLL;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.BusinessException;

@WebServlet("/detailArticle/*")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBLL articleBLL;

	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pathInfo = request.getPathInfo();
			String[] pathParts = pathInfo.split("/");
			String idArticle = pathParts[1];
			Article article = articleBLL.selectById(Integer.parseInt(idArticle));
			if (article == null) {
				throw new BusinessException("Cet article n'existe pas");
			}

			request.setAttribute("article", article);
			request.getRequestDispatcher("/detailArticle.jsp").forward(request, response);
		} catch (NumberFormatException nbEx) {
			handleError(request, response, "L'id dans l'url n'est pas un nombre. Veuillez ne pas jouer avec l'url");
		} catch (BusinessException dee) {
			handleError(request, response, dee.getMessage());
		} catch (Exception e) {
			handleError(request, response, "Une erreur est survenue lors du traitement");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Utilisateur util = (Utilisateur) request.getSession().getAttribute("connected");

			if (!util.isActif()) {
				throw new BusinessException("Votre compte est désactivé. Vous ne pouvez pas faire d'enchère");
			}

			String pathInfo = request.getPathInfo();
			String[] pathParts = pathInfo.split("/");
			int idArticle = Integer.parseInt(pathParts[1]);
			int proposition = Integer.parseInt(request.getParameter("proposition"));

			Article article = articleBLL.selectById(idArticle);

			if (article == null) {
				throw new BusinessException("Cet article n'existe pas");
			}
			articleBLL.checkProposition(article, proposition, util);
			request.getRequestDispatcher("/accueil").forward(request, response);

		} catch (NumberFormatException nbEx) {
			handleError(request, response, "L'id dans l'url n'est pas un nombre. Veuillez ne pas jouer avec l'url");
		} catch (BusinessException dee) {
			handleError(request, response, dee.getMessage());
		} catch (Exception e) {
			handleError(request, response, "Une erreur est survenue lors du traitement");
		}
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("messageError", errorMessage);
		request.getRequestDispatcher("/accueil").forward(request, response);
	}
}
