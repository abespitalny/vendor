package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "2298";
	private static final String CONN_STRING = "jdbc:mysql://localhost/world";
	
	public static Connection getMyConnection() throws SQLException {
		return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	}
}
