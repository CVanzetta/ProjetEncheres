package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.EnchereArticle;

public class EnchereArticleDAOJdbcImpl implements EnchereArticleDAO {
	private static final String SELECTJOINALL = "select a.no_article,a.nom_article,e.montant_enchere,a.prix_initial,a.date_fin_enchere,a.etat_vente,u.pseudo, s.picture, c.libelle from ARTICLES_VENDUS a \r\n"
			+ "left join ENCHERES e on a.no_article = e.no_article and (montant_enchere = (\r\n"
			+ "  SELECT MAX(montant_enchere)\r\n" + "  FROM encheres\r\n" + "  WHERE no_article = a.no_article\r\n"
			+ "))\r\n" + "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur \r\n"
			+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
			+ "left join S on s.no_article = a.no_article \r\n" + "WHERE a.etat_vente = 'EC'";
	private static final String SELECTJOINCAT = "select c.libelle," + "a.no_article," + "a.nom_article,"
			+ "e.montant_enchere," + "a.prix_initial," + "a.date_fin_enchere," + "a.etat_vente," + "u.pseudo, "
			+ "s.picture " + "from ARTICLES_VENDUS a "
			+ "left join ENCHERES e on a.no_article = e.no_article and (montant_enchere = (\r\n"
			+ "  SELECT MAX(montant_enchere)\r\n" + "  FROM encheres\r\n" + "  WHERE no_article = a.no_article\r\n"
			+ "))\r\n" + "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
			+ "left join s on s.no_article = a.no_article " + "WHERE a.etat_vente = 'EC' AND c.libelle = ?";
	private static final String SELECTJOINLIKE = "select a.no_article," + "a.nom_article," + "e.montant_enchere,"
			+ "a.prix_initial," + "a.date_fin_enchere," + "a.etat_vente," + "u.pseudo, " + "s.picture "
			+ "from ARTICLES_VENDUS a "
			+ "left join ENCHERES e on a.no_article = e.no_article and (montant_enchere = (\r\n"
			+ "  SELECT MAX(montant_enchere)\r\n" + "  FROM encheres\r\n" + "  WHERE no_article = a.no_article\r\n"
			+ "))\r\n" + "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			+ "left join s on s.no_article = a.no_article "
			+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
			+ "WHERE a.etat_vente = 'EC' AND a.nom_article like ?";
	private static final String SELECTJOINCATLIKE = "select c.libelle," + "a.no_article," + "a.nom_article,"
			+ "e.montant_enchere," + "a.prix_initial," + "a.date_fin_enchere," + "a.etat_vente," + "u.pseudo, "
			+ "s.picture " + "from ARTICLES_VENDUS a "
			+ "left join ENCHERES e on a.no_article = e.no_article and (montant_enchere = (\r\n"
			+ "  SELECT MAX(montant_enchere)\r\n" + "  FROM encheres\r\n" + "  WHERE no_article = a.no_article\r\n"
			+ "))\r\n" + "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
			+ "left join s on s.no_article = a.no_article "
			+ "WHERE a.etat_vente = 'EC' and c.libelle = ? AND a.nom_article like ? ";
	private static final String SELECTJOINUSER = "select c.libelle," + "a.no_article," + "a.nom_article,"
			+ "e.montant_enchere," + "a.prix_initial," + "a.date_fin_enchere," + "a.etat_vente," + "u.pseudo,"
			+ "s.picture " + "from ARTICLES_VENDUS a " + "left join ENCHERES e on a.no_article = e.no_article "
			+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
			+ "left join s on s.no_article = a.no_article " + "where u.pseudo = ?";
	private static final String SELECTJOINENCHERE = "select distinct av.*, u.pseudo as encherisseur, en.date_enchere, i.picture,c.libelle, en.montant_enchere from ENCHERES en  \r\n"
			+ "inner join ARTICLES_VENDUS av on av.no_article = en.no_article\r\n"
			+ "left join S i on i.no_article = av.no_article\r\n"
			+ "inner join UTILISATEURS u on u.no_utilisateur = en.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on av.no_categorie=c.no_categorie\r\n"
			+ "where u.pseudo = ? and av.etat_vente = 'EC' ";
	private static final String SELECTJOINENCHEREVD = "select distinct av.*, u.pseudo as encherisseur, en.date_enchere, i.picture,c.libelle, MAX(en.montant_enchere) as montant_enchere from ENCHERES en  \r\n"
			+ "inner join ARTICLES_VENDUS av on av.no_article = en.no_article\r\n"
			+ "left join S i on i.no_article = av.no_article\r\n"
			+ "inner join UTILISATEURS u on u.no_utilisateur = en.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on av.no_categorie=c.no_categorie\r\n"
			+ "where u.pseudo = ? and av.etat_vente = 'VD' \r\n"
			+ "GROUP BY av.no_article, av.nom_article,av.description,av.date_debut_enchere,av.date_fin_enchere,av.,av.prix_initial,av.prix_vente,av.no_utilisateur,av.no_categorie,av.etat_vente,u.pseudo,en.date_enchere,i.picture,c.libelle\r\n";

	@Override
	public List<EnchereArticle> selectJoin() {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTJOINALL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");
				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, pseudo, montant_enchere, null);

				list.add(ea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinCat(String categoriesql) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINCAT);
			ps.setString(1, categoriesql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");

				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, pseudo, montant_enchere, null);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinLike(String nom_articlelike) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINLIKE);
			ps.setString(1, "%" + nom_articlelike + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");

				EnchereArticle ea = new EnchereArticle(no_article, nom_article, prix_initial, date_fin_enchere,
						etat_vente, pseudo, montant_enchere, null);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINCATLIKE);
			ps.setString(1, categoriesql);
			ps.setString(2, "%" + nom_articlelike + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");

				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, pseudo, montant_enchere, null);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinByUser(String user) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINUSER);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");

				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, pseudo, montant_enchere, null);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinByUserEnchere(String user) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINENCHERE);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				Integer montant_enchere = rs.getInt("montant_enchere");
				Integer no_utilisateur = rs.getInt("no_utilisateur");
				String encherisseur = rs.getString("encherisseur");

				// Utilisez le constructeur approprié ici
				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, montant_enchere, null, no_utilisateur, encherisseur);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EnchereArticle> selectJoinByUserEnchereVD(String user) {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINENCHEREVD);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				Integer montant_enchere = rs.getInt("montant_enchere");
				Integer no_utilisateur = rs.getInt("no_utilisateur");
				String encherisseur = rs.getString("encherisseur");

				// Utilisez le constructeur approprié ici
				EnchereArticle ea = new EnchereArticle(categorie, no_article, nom_article, prix_initial,
						date_fin_enchere, etat_vente, montant_enchere, null, no_utilisateur, encherisseur);

				list.add(ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
