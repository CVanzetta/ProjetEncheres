package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public interface ArticleDAO {
	public void insertArticle(Article article, int ID) throws BusinessException;

	public int getLastNoArticle(int ID) throws BusinessException;
	
	public Article selectById(int articleId) throws BusinessException;
	
	public List<Article> getArticlesEnCours() throws BusinessException;
}
