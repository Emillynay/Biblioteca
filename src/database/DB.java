package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
	private static final String User = "root";
	private static final String Password = "Emilly@1";
	
	public static Connection getConection() throws SQLException {
		return DriverManager.getConnection(URL, User, Password);
	}
}
