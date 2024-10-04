package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

	private static final String SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> categories = new ArrayList<>();

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ALL_CATEGORIES);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				categories.add(categorie);
			}
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			throw be;
		}

		return categories;
	}

	@Override
	public Categorie getCategoriebyId(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer idCateg, String categorie) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object selectByLibelle(String categorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Article> allArticleByCateg(int idCategorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idCategorie) {
		// TODO Auto-generated method stub

	}
}
