package com.EIMA.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// create an object of SingleObject
	private static Connection instance = privateGetConnection();

	// make the constructor private so that this class cannot be
	// instantiated
	private DBConnection() {
	}

	// Get the only object available
	public static Connection getConnection() {
		return instance;
	}

	private static Connection privateGetConnection() {
		String driverJDBC = "org.postgresql.Driver";
        try {
			Class.forName(driverJDBC);
		} catch (ClassNotFoundException e1) {
			System.out.println("Class Not Found");
			e1.printStackTrace();
		}
		try {
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			if(dbUrl == null || dbUrl.isEmpty()){
				System.out.println("DB URL NOT SET See Doc");
			}
			return DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			System.out.println("SQL Error;");
			e.printStackTrace();
		}
		return null;
	}
}
