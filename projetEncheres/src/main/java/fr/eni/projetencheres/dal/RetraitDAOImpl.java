package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Retrait;

public class RetraitDAOImpl implements RetraitDAO {
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (?,?,?,?)";
	
	
	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		String requete = INSERT_RETRAIT;
		
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(requete);
        	
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			
			stmt.executeUpdate();
        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void getRetraitByUtilisateurId(int utilisateurId) throws BusinessException {
		// TODO Auto-generated method stub

	}
	



}
