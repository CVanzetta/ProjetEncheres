package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException;
	public Utilisateur getUtilisateurByMail(String mail) throws BusinessException;
	public List<Utilisateur> getAllUtilisateurs() throws BusinessException;
	public Utilisateur getUtilisateur(String pseudo) throws BusinessException;
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public void supprimerUtilisateur(String pseudo) throws BusinessException;
	public int getUtilisateurById(String pseudo) throws BusinessException;
	public void addCredit(Utilisateur utilACrediter, int offre);
	public Utilisateur selectById(int utilisateurId);
	public String[] getAdresseUtilisateur(String pseudo) throws BusinessException;
}
