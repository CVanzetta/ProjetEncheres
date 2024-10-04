package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/encheres")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    ArticleDAO articleDAO = DAOFactory.getArticleDAO();

	    try {
	        List<Article> articles = articleDAO.getArticlesEnCours();
	        request.setAttribute("articles", articles);
	    } catch (BusinessException e) {
	        e.printStackTrace();
	    }
	    request.getRequestDispatcher("WEB-INF/jsp/fr/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
