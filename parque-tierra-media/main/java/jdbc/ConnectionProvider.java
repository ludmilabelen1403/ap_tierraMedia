package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url = "jdbc:sqlite:tierramedia.db";
	private static Connection conn;
	public static Connection getConnection() throws SQLException{
		if (conn== null) {
			conn = DriverManager.getConnection(url);
		}return conn;
	}
	

}
