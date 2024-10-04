package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.EnchereArticle;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.EnchereArticleDAO;

public class EnchereArticleBLL {
	private EnchereArticleDAO dao;

	public EnchereArticleBLL() {
		dao = DAOFactory.getEnchereArticleDAO();
	}

	public List<EnchereArticle> selectJoin() {
		return dao.selectJoin();
	}

	public List<EnchereArticle> selectJoinLike(String nom_articlelike) {
		return dao.selectJoinLike(nom_articlelike);
	}

	public List<EnchereArticle> selectJoinCat(String categoriesql) {
		return dao.selectJoinCat(categoriesql);
	}

	public List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike) {
		return dao.selectJoinCatLike(categoriesql, nom_articlelike);
	}

	public List<EnchereArticle> selectJoinByUser(String user) {
		return dao.selectJoinByUser(user);
	}

	public List<EnchereArticle> selectJoinByUserEnchere(String user) {
		return dao.selectJoinByUserEnchere(user);
	}

	public List<EnchereArticle> selectJoinByUserEnchereVD(String user) {
		return dao.selectJoinByUserEnchereVD(user);
	}

	public static List<EnchereArticle> getFilteredEncheres(String categorie, String textArticle) throws BusinessException {
		List<EnchereArticle> filteredEncheres = null;

		try {

			EnchereArticleDAO enchereArticleDAO = DAOFactory.getEnchereArticleDAO();

			if (categorie != null && !categorie.isEmpty() && textArticle != null && !textArticle.isEmpty()) {
				filteredEncheres = enchereArticleDAO.selectJoinCatLike(categorie, textArticle);
			} else if (categorie != null && !categorie.isEmpty()) {
				filteredEncheres = enchereArticleDAO.selectJoinCat(categorie);
			} else if (textArticle != null && !textArticle.isEmpty()) {
				filteredEncheres = enchereArticleDAO.selectJoinLike(textArticle);
			} else {
				filteredEncheres = enchereArticleDAO.selectJoin();
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new BusinessException("Une erreur s'est produite lors de la récupération des enchères filtrées.");
		}

		return filteredEncheres;
	}

}