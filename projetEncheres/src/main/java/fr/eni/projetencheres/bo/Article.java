package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Article {
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Integer prixInitial;
	private Integer prixVente;
	private Integer utilisateurId;
	private Integer noCategorie;
	private Enchere enchere;
	private String etatVente;
	
	

	public Article() {
		super();
	}

	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin,
			Integer prixInitial, Integer prixVente, Integer utilisateurId, Integer noCategorie) {
		
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateurId = utilisateurId;
		this.noCategorie = noCategorie;
	}

	public Article(String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, Integer prixInitial,
			Integer prixVente, Integer utilisateurId, Integer noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateurId = utilisateurId;
		this.noCategorie = noCategorie;
	}

	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public Integer getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prixInitial=" + prixInitial + ", prixVente="
				+ prixVente + ", utilisateurId=" + utilisateurId + ", noCategorie=" + noCategorie + "]";
	}

//	 public void setImage(Image image) {
//	        this.image = image;
//	    }

	public String getNom() {
		return nomArticle;
	}

	public boolean isActif() {
		return "actif".equals(etatVente);
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

}
