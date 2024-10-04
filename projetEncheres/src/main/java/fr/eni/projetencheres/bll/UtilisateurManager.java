package fr.eni.projetencheres.bll;

import java.util.List;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance;

	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {

		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public void seConnecter(String identifiant, String mdp) throws BusinessException {
		Utilisateur user = null;
		if (identifiant.contains("@"))
			user = utilisateurDAO.getUtilisateurByMail(identifiant);
		else
			user = utilisateurDAO.getUtilisateurByPseudo(identifiant);

		if (user == null || !mdp.equals(user.getMot_de_passe())) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.IDENTIFIANT_KO);
			throw be;
		}
	}

	public List<Utilisateur> allUtilisateurs() throws BusinessException {
		List<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateurs();
		return listeUtilisateurs;
	}

	public Utilisateur voirUtilisateur(String pseudo) throws BusinessException {
		Utilisateur user = utilisateurDAO.getUtilisateur(pseudo);
		return user;
	}

	public boolean authentifier(String identifiant, String mdp) throws BusinessException {
		Utilisateur user = null;

		// Vérifiez si l'identifiant est une adresse e-mail ou un pseudo
		if (identifiant.contains("@")) {
			user = utilisateurDAO.getUtilisateurByMail(identifiant);
		} else {
			user = utilisateurDAO.getUtilisateurByPseudo(identifiant);
		}

		// Si aucun utilisateur correspondant n'est trouvé, l'authentification échoue
		if (user == null) {
			return false;
		}

		String motDePasseStocke = user.getMot_de_passe();
		String motDePasseHache = hashSHA256(mdp);

		// Comparez le mot de passe haché avec celui stocké
		if (motDePasseHache.equals(motDePasseStocke)) {
			return true;
		} else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.IDENTIFIANT_KO);
			throw be;
		}
	}

	public String hashSHA256(String motDePasse) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder(2 * hash.length);

			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public void modifierProfil(Utilisateur user) throws BusinessException {
		utilisateurDAO.modifierUtilisateur(user);
		}
	
	public void supprimerUtilisateur(String pseudo) throws BusinessException {
		utilisateurDAO.supprimerUtilisateur(pseudo);
	}
	
	public int getID(String pseudo) {
		int ID = 0;
		try {
			ID = utilisateurDAO.getUtilisateurById(pseudo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return ID;
	}
}
