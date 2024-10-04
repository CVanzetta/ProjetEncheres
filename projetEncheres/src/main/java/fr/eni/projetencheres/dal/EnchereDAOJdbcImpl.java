package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {

	private UtilisateurDAO daoUtilisateur;

	private static final String DELETE = "DELETE from encheres where no_article = ?;";
	private static final String DELETEBYUSER = "DELETE from encheres where no_utilisateur = ?;";
	private static final String SELECTENCHERE = "SELECT *\r\n" + "FROM encheres\r\n" + "WHERE no_article = ? \r\n"
			+ "AND montant_enchere = (\r\n" + "  SELECT MAX(montant_enchere)\r\n" + "  FROM encheres\r\n"
			+ "  WHERE no_article = ?\r\n" + ")";
	private static final String INSERT = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values(?, ?, ?, ?);";
	private static final String UPDATE = "update encheres set no_utilisateur = ?, date_enchere = ?, montant_enchere = ? where no_article = ?";

	public EnchereDAOJdbcImpl() {
		setDaoUtilisateur(DAOFactory.getUtilisateurDAO());
	}

	@Override
	public Enchere getEnchereByArticle(Article article) {
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement ps = cnx.prepareStatement(SELECTENCHERE);
			ps.setInt(1, article.getNoArticle());
			ps.setInt(2, article.getNoArticle());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Timestamp dateEnchere = rs.getTimestamp("date_enchere");
				int montant = rs.getInt("montant_enchere");
				int noUtil = rs.getInt("no_utilisateur");
				new Enchere(noUtil, article.getNoArticle(), dateEnchere, montant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public void createEnchere(Article article, Utilisateur util, int montantEnchere) {
		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement ps = cnx.prepareStatement(INSERT);

			Date dateEnchere = new Date(System.currentTimeMillis());

			ps.setInt(1, util.getUtilisateurId());
			ps.setInt(2, article.getNoArticle());
			ps.setDate(3, dateEnchere);
			ps.setInt(4, montantEnchere);

			ps.executeUpdate();
			ps.close();
			cnx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEnchere(Article article, Utilisateur util, int montantEnchere) {
		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement ps = cnx.prepareStatement(UPDATE);

			Date dateEnchere = new Date(System.currentTimeMillis());

			ps.setInt(1, util.getUtilisateurId());
			ps.setDate(2, dateEnchere);
			ps.setInt(3, montantEnchere);
			ps.setInt(4, article.getNoArticle());

			ps.executeUpdate();
			ps.close();
			cnx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByUser(Utilisateur util) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(DELETEBYUSER);
			ps.setInt(1, util.getUtilisateurId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UtilisateurDAO getDaoUtilisateur() {
		return daoUtilisateur;
	}

	public void setDaoUtilisateur(UtilisateurDAO daoUtilisateur) {
		this.daoUtilisateur = daoUtilisateur;
	}

}