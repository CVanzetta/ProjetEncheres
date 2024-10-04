package fr.eni.projetencheres.bo;

public class Utilisateur {	
	private Integer utilisateurId;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String code_postal;
	private String ville;
	private String mot_de_passe;
	private int credit;
	private Boolean administrateur;
	
	
	public Utilisateur(Integer utilisateurId, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, int credit, Boolean administrateur) {
		super();
		this.utilisateurId = utilisateurId;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}


	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, int credit, Boolean administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}


	public Integer getUtilisateurId() {
		return utilisateurId;
	}


	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCode_postal() {
		return code_postal;
	}


	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	public Boolean getAdministrateur() {
		return administrateur;
	}


	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}


	public boolean isActif() {
		return false;
	}
	
}
