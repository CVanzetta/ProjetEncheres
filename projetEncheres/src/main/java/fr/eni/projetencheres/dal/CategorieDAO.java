package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public interface CategorieDAO {
	
	public Categorie getCategoriebyId(String id) throws BusinessException;

	public List<Categorie> selectAll() throws BusinessException;

	public void update(Integer idCateg, String categorie);

	public Object selectByLibelle(String categorie);

	public void insert(Categorie categorie);

	public List<Article> allArticleByCateg(int idCategorie);

	public void delete(int idCategorie);
	
	

}
