package fr.eni.projetencheres.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {
	private static DataSource dataSource;

	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erreur lors de l'initialisation du pool de connexions.");
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
