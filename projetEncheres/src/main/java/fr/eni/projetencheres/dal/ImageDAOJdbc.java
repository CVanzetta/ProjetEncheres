package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetencheres.bo.Image;


public class ImageDAOJdbc {

	private final static String SELECTBYID_QUERY = "SELECT * FROM IMAGES WHERE no_article = ?;";
	private final static String INSERT_QUERY = "INSERT INTO IMAGES(no_article, picture) VALUES (?,?);";

	public Image selectby(int id) throws SQLException {
		Image image = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECTBYID_QUERY);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				image = new Image(rs.getString("picture"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Echec de l'extraction", e);
		}
		return image;
	}

	public Image insert(Image newImage) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_QUERY);
			pstmt.setInt(1, newImage.getArticle().getNoArticle());
			pstmt.setString(2, newImage.getPicture());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Echec de création de l'image", e);
		}
		return newImage;

	}
}