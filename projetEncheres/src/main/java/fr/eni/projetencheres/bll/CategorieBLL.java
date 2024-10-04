package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;

public class CategorieBLL {
	private CategorieDAO dao;
	private CategorieDAO categorieDAO;

	public CategorieBLL() {
		// Initialise le DAO de catégories une seule fois
		dao = DAOFactory.getCategorieDAO();
	}

	// Méthode pour récupérer toutes les catégories
	public List<Categorie> selectAll() throws BusinessException {
		try {
			// Utilise le DAO pour récupérer la liste des catégories
			return dao.selectAll();
		} catch (BusinessException e) {
			// Gère les exceptions en les remontant ou en les traitant de manière appropriée
			throw new BusinessException("Une erreur s'est produite lors de la récupération des catégories.", e);
		}
	}

	public List<Categorie> getAllCategories() throws BusinessException {
		try {
			// Utilisez la couche de données pour récupérer toutes les catégories
			return categorieDAO.selectAll(); // Assurez-vous d'avoir une instance de CategorieDAO
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Une erreur s'est produite lors de la récupération des catégories.");
		}
	}

}
