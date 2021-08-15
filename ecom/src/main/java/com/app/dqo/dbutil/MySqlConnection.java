package com.app.dqo.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private MySqlConnection() {
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ecom";
		String user = "root";
		String password  = "Deepak#1818";
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
