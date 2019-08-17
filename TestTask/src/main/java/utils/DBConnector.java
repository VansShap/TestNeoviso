package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ConnectionException;

public class DBConnector {
	private final static Connection CONNECTION = createConnection();

	private static final String CLASS_NAME = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/testneovisodb";
	private static final String USER = "postgres";
	private static final String PASSWORD = "vansvins28";
	
	private DBConnector() {
		
	}
	
	private static Connection createConnection() {
		try {
			Class.forName(CLASS_NAME);
			return DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new ConnectionException("Database connection error!");
		}
		catch (SQLException e) {
			throw new ConnectionException("Database connection error!");
		}
	}
	
	public static Connection getConnection() {
		return CONNECTION;
	}

	public static void closeConnection() {
		try {
			if (CONNECTION != null) {
				CONNECTION.close();
			}
		} catch (SQLException e) {
			System.err.println("Error with connection close.");
		}
	}

	public static void closeStatements(Statement... statements) {
		for (Statement statement : statements) {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.err.println("Error with statement close.");
			}
		}
	}

}
