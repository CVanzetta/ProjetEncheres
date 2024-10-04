package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletInscription() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/fr/inscription.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String rue = request.getParameter("rue");
		String motDePasse = request.getParameter("motDePasse");
		String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");

		// Générez un hachage sécurisé du mot de passe en SHA-256
		String hashedPassword = hashSHA256(motDePasse);

		List<String> erreurs = new ArrayList<>();

		if (nom.isEmpty()) {
			erreurs.add("Le nom est requis.");
		}

		if (prenom.isEmpty()) {
			erreurs.add("Le prénom est requis.");
		}

		if (!emailValide(email)) {
			erreurs.add("L'adresse e-mail est invalide.");
		}

		if (!codePostal.matches("^\\d{5}$")) {
			erreurs.add("Le code postal doit contenir 5 chiffres.");
		}

		if (motDePasse.length() < 4) {
			erreurs.add("Le mot de passe est trop court.");
		}

		if (!confirmationMotDePasse.equals(motDePasse)) {
			erreurs.add("La confirmation du mot de passe ne correspond pas.");
		}

		if (telephone != null && !telephone.matches("^\\d{10}$")) {
			erreurs.add("Le numéro de téléphone doit contenir 10 chiffres.");
		}

		if (ville != null && !ville.matches("^[a-zA-Z]+$")) {
			erreurs.add("La ville doit contenir uniquement des lettres.");
		}

		if (rue.isEmpty()) {
			erreurs.add("La rue est requise.");
		}

		if (!erreurs.isEmpty()) {
			request.setAttribute("erreurs", erreurs);
			request.getRequestDispatcher("/WEB-INF/jsp/fr/inscription.jsp").forward(request, response);
			return;
		}

		boolean inscriptionValide = validerInscription(pseudo, email, motDePasse);

		if (inscriptionValide) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				// Récupérer la connexion à la base de données depuis le contexte
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/pool_cnx");

				// Établir la connexion en utilisant la source de données
				conn = ds.getConnection();
				// Requête SQL pour insérer l'utilisateur
				String sql = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, code_postal, ville, rue, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pseudo);
				pstmt.setString(2, nom);
				pstmt.setString(3, prenom);
				pstmt.setString(4, email);
				pstmt.setString(5, telephone);
				pstmt.setString(6, codePostal);
				pstmt.setString(7, ville);
				pstmt.setString(8, rue);
				pstmt.setString(9, hashedPassword); // Utilisez le mot de passe haché ici
				pstmt.setInt(10, 0); // Crédit initial à 0
				pstmt.setInt(11, 0); // Aucun droit administrateur par défaut

				// Exécution de la requête
				pstmt.executeUpdate();

				// Redirection vers la page d'accueil
				response.sendRedirect(request.getContextPath() + "/encheres");
				//request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
				//response.sendRedirect("accueil.jsp");
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
				 if (e instanceof SQLException && ((SQLException) e).getSQLState().equals("23000")) {
					// Une violation de contrainte d'unicité s'est produite
					if (e.getMessage().contains("UC_Pseudo")) {
						// Le pseudo est déjà utilisé
						request.setAttribute("inscriptionErreur",
								"Le pseudo est déjà utilisé. Veuillez en choisir un autre.");
					} else if (e.getMessage().contains("UC_Email")) {
						// L'email est déjà utilisé
						request.setAttribute("inscriptionErreur",
								"L'adresse email est déjà utilisée. Veuillez en choisir une autre.");
					} else {
						// Autre erreur SQL
						request.setAttribute("inscriptionErreur", "Erreur lors de l'inscription. Veuillez réessayer.");
					}
					request.getRequestDispatcher("/WEB-INF/jsp/fr/inscription.jsp").forward(request, response);
				} else {
					e.printStackTrace(); // Ou gestion d'autres erreurs SQL
				}
			} finally {
				// Fermer les ressources JDBC
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			// Gérer le cas où l'inscription n'est pas valide
			// Rediriger l'utilisateur vers la page d'inscription avec un message d'erreur
			request.setAttribute("inscriptionErreur", "L'inscription a échoué. Veuillez réessayer.");
			request.getRequestDispatcher("/WEB-INF/jsp/fr/inscription.jsp").forward(request, response);
		}
	}

	private String hashSHA256(String motDePasse) {
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
			e.printStackTrace();
			// Gérez l'exception selon vos besoins
			return null;
		}
	}

	private boolean emailValide(String email) {
		return email.contains("@");
	}

	private boolean validerInscription(String pseudo, String email, String motDePasse) {
		boolean emailValide = emailValide(email);
		boolean pseudoValide = pseudoValide(pseudo);
		boolean motDePasseValide = motDePasse.length() >= 4;

		return emailValide && pseudoValide && motDePasseValide;
	}

	private boolean pseudoValide(String pseudo) {
		return pseudo.matches("^[a-zA-Z0-9]+$");
	}
}
