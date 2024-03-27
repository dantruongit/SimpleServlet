package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/ecommerce";
		String username = "root";
		String password = "admin";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			return null;
		}
	}
}
