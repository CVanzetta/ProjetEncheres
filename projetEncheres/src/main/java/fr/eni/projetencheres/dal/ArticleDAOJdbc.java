package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public class ArticleDAOJdbc implements ArticleDAO {

	private static final String SELECT_ARTICLE_BY_ID_SQL = "SELECT * FROM articles WHERE id = ?";
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SELECT_LAST_NOARTICLE = "SELECT TOP 1 no_article FROM ARTICLES_VENDUS WHERE no_utilisateur = ? ORDER BY no_article DESC";
	private static final String SELECT_ARTICLES_ENCOURS =  "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres >= GETDATE()";

	public Article selectById(int articleId) throws BusinessException {
		Article article = null;
		try (Connection connection = DataSourceProvider.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID_SQL);
			preparedStatement.setInt(1, articleId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String description = resultSet.getString("description");
				int prixInitial = resultSet.getInt("prix_initial");
				int categorieId = resultSet.getInt("categorie_id");

				article = new Article(id, nom, description, null, null, prixInitial, null, null, categorieId);
			}
		} catch (SQLException e) {

			throw new BusinessException();
		}

		return article;
	}

	@Override
	public void insertArticle(Article article, int ID) {
		String requete = INSERT;

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(requete);

			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, Date.valueOf(article.getDateDebut()));
			stmt.setDate(4, Date.valueOf(article.getDateFin()));
			stmt.setInt(5, article.getPrixInitial());
			stmt.setInt(6, article.getPrixVente());
			stmt.setInt(7, ID);
			stmt.setInt(8, article.getNoCategorie());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getLastNoArticle(int ID) throws BusinessException {
		String requete = SELECT_LAST_NOARTICLE;
		int lastNoArticle = -1;

		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);

			preparedStatement.setInt(1, ID);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					lastNoArticle = rs.getInt("no_article");
				}
			}
		} catch (SQLException e) {
			throw new BusinessException();
		}

		return lastNoArticle;
	}

	@Override
	public List<Article> getArticlesEnCours() throws BusinessException {
		List<Article> articles = new ArrayList();
		String requete = SELECT_ARTICLES_ENCOURS;

	    try (Connection connection = ConnectionProvider.getConnection() ) {
	         PreparedStatement preparedStatement = connection.prepareStatement(requete);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                Article article = new Article(
	                		rs.getInt("no_article"),
	                		rs.getString("nom_article"),
	                		rs.getString("description"),
	                		rs.getDate("date_debut_encheres").toLocalDate(),
	                		rs.getDate("date_fin_encheres").toLocalDate(),
	                		rs.getInt("prix_initial"),
	                		rs.getInt("prix_vente"),
	                		rs.getInt("no_utilisateur"),
	                		rs.getInt("no_categorie")
	                );
	                articles.add(article);
	            }
	        }
	    } catch (SQLException e) {
	        throw new BusinessException();
	        // TODO : Gérer erreurs
	    }

	    return articles;
	}
}
