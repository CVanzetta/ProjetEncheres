package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.ArticleDAOJdbc;

public class ArticleBLL {
	private ArticleDAO articleDAO;

	public ArticleBLL() {
		articleDAO = new ArticleDAOJdbc(); // Utilisez la classe concrète
	}

	public Article selectById(int articleId) throws Exception {
		try {
			return articleDAO.selectById(articleId);
		} catch (Exception e) {
			throw new Exception("Erreur lors de la récupération de l'article : " + e.getMessage());
		}
	}

	public void checkProposition(Article article, int proposition, Utilisateur util) throws BusinessException {
		// Vérifier si l'utilisateur peut faire une proposition
		if (!util.isActif()) {
			throw new BusinessException("Votre compte est désactivé. Vous ne pouvez pas faire d'enchère.");
		}

		// Vérifier si l'article est actif
		if (!article.isActif()) {
			throw new BusinessException("Cet article n'est plus disponible pour des propositions.");
		}

		// Vérifier si la proposition est valide
		if (proposition <= article.getPrixVente()) {
			throw new BusinessException("La proposition doit être supérieure au prix actuel de l'article.");
		}

		// Si toutes les vérifications passent, mettre à jour le prix de vente de
		// l'article
		article.setPrixVente(proposition);


	}

}
