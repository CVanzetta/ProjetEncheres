package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.bo.EnchereArticle;

public interface EnchereArticleDAO {

	List<EnchereArticle> selectJoin();

	List<EnchereArticle> selectJoinCat(String categoriesql);

	List<EnchereArticle> selectJoinLike(String nom_articlelike);

	List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike);

	List<EnchereArticle> selectJoinByUser(String user);

	List<EnchereArticle> selectJoinByUserEnchere(String user);

	List<EnchereArticle> selectJoinByUserEnchereVD(String user);
}