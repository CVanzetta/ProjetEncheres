package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;


/*
 * Définition des méthodes utilisées auprès de la BDD
 */
public interface EnchereDAO {

	public Enchere getEnchereByArticle(Article article);

	public void createEnchere(Article article, Utilisateur util, int montantEnchere);

	public void updateEnchere(Article article, Utilisateur util, int montantEnchere);

	public void delete(int noArticle);

	public void deleteByUser(Utilisateur util);
}