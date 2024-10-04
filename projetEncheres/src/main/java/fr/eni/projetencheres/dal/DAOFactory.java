package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbc();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}

	public static EnchereArticleDAO getEnchereArticleDAO() {
		return new EnchereArticleDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}
}
