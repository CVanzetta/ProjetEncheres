package fr.eni.projetencheres.bo;

import java.sql.Timestamp;

public class Enchere {

	private int utilisateurId;
	private int noArticle;
	private Timestamp dateEnchere;
	private int montantEnchere;

	// Les getters et setters
	
	public Enchere(int utilisateurId, int noArticle, Timestamp dateEnchere, int montantEnchere) {
		super();
		this.utilisateurId = utilisateurId;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere() {
		super();
	}

	public int getNoUtilisateur() {
		return utilisateurId;
	}

	public void setNoUtilisateur(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

}
