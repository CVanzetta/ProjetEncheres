package fr.eni.projetencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.CodesResultatDAL;
import fr.eni.projetencheres.dal.UtilisateurDAO;
import fr.eni.projetencheres.dal.ConnectionProvider;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static final String SELECT_USER_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email = ?;";
	private static final String SELECT_ALL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS";
	private static final String SELECT_USER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=? WHERE pseudo = ?;";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USERID = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USER_ADRESS = "SELECT pseudo, rue, code_postal, ville FROM UTILISATEURS WHERE pseudo = ?;";
	
	private Utilisateur getUtilisateurByLogin(String login, String requete) throws BusinessException {
		Utilisateur user = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement psmt = cnx.prepareStatement(requete)){
			
			//Remplacer "?" de " = ?"
			psmt.setString(1, login);
			
			//Exécuter requête
			try(ResultSet rs = psmt.executeQuery()){
				//Si le résultat est bon
				if(rs.next()) {
					user = new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return user;
	}
	
	@Override
	public Utilisateur getUtilisateurByMail(String mail) throws BusinessException {
		return getUtilisateurByLogin(mail, SELECT_USER_BY_EMAIL);
	}
	
	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
		return getUtilisateurByLogin(pseudo, SELECT_USER_BY_PSEUDO);
	}
	
	public List<Utilisateur> getAllUtilisateurs() throws BusinessException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL)){			
			try(ResultSet rs = psmt.executeQuery()){
				while(rs.next()) {
					Utilisateur utilisateur = new Utilisateur(rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"));
					listeUtilisateurs.add(utilisateur);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return listeUtilisateurs;
	}
	
	public Utilisateur getUtilisateur(String pseudo) throws BusinessException {
		Utilisateur user = null;
		String requete = SELECT_USER;
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement psmt = cnx.prepareStatement(requete)){
			
			psmt.setString(1, pseudo);
			
			try(ResultSet rs = psmt.executeQuery()){
				if(rs.next()) {
					user = new Utilisateur(rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return user;
	}

	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		String requete = UPDATE_USER;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(requete)) {

			psmt.setString(1, utilisateur.getNom());
			psmt.setString(2, utilisateur.getPrenom());
			psmt.setString(3, utilisateur.getEmail());
			psmt.setString(4, utilisateur.getTelephone());
			psmt.setString(5, utilisateur.getRue());
			psmt.setString(6, utilisateur.getCode_postal());
			psmt.setString(7, utilisateur.getVille());
			psmt.setString(8, utilisateur.getPseudo());

			psmt.executeUpdate();

		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
	}

	public void supprimerUtilisateur(String pseudo) throws BusinessException {
		String requete = DELETE_USER;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(requete)) {

			psmt.setString(1, pseudo);

			psmt.execute();

		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
	}

	public int getUtilisateurById(String pseudo) throws BusinessException {
		int ID = 0;
		String requete = SELECT_USERID;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(requete)) {

			psmt.setString(1, pseudo);

			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					ID = rs.getInt("no_utilisateur");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return ID;
	}

	@Override
	public String[] getAdresseUtilisateur(String pseudo) throws BusinessException {
		String[] adresse = new String[3];
		String requete = SELECT_USER_ADRESS;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(requete)) {

			psmt.setString(1, pseudo);

			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					adresse[0] = rs.getString("rue");
					adresse[1] = rs.getString("code_postal");
					adresse[2] = rs.getString("ville");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return adresse;
	}

	@Override
	public void addCredit(Utilisateur utilACrediter, int offre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur selectById(int utilisateurId) {
		// TODO Auto-generated method stub
		return null;
	}
}
