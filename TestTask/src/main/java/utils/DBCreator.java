package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ConnectionException;

public class DBCreator {

	private static boolean checker;
	private static Statement st = null;
	
	private DBCreator() {

	}
	
	public static boolean getChecker() {
		return checker;
	}
	
	public static void deleteDB() {
		Connection conn = null;

    	try {
    		Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "vansvins28");
			st = conn.createStatement();
			st.executeUpdate(Constants.SQL_DROP_DB_IF_EXISTS_QUERY);
			st.executeUpdate(Constants.SQL_CREATE_DB_QUERY);
		} catch (ClassNotFoundException e) {
			throw new ConnectionException("Database connection error!");
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ConnectionException("Database connection error!");
		}
    	finally {
				try {
						if (conn != null) {
							conn.close();
						}
						if (st != null) {
							st.close();
						}
				}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createDB() {
    	try {
	    	st = DBConnector.getConnection().createStatement();
	    	st.executeUpdate(Constants.SQL_CREATE_TABLE_BOOKS);
	    	st.executeUpdate(Constants.SQL_CREATE_TABLE_USERS);
	    	st.executeUpdate(Constants.SQL_CREATE_TABLE_SHOPS);
	    	st.executeUpdate(Constants.SQL_CREATE_TABLE_BOOK_USER);
	    	st.executeUpdate(Constants.SQL_CREATE_TABLE_BOOK_SHOP);
	    	st.executeUpdate(Constants.SQL_INSERT_INTO_BOOKS_QUERY);
	    	st.executeUpdate(Constants.SQL_INSERT_INTO_USERS_QUERY);
	    	st.executeUpdate(Constants.SQL_INSERT_INTO_SHOPS_QUERY);
	    	st.executeUpdate(Constants.SQL_INSERT_INTO_BOOK_SHOP_QUERY);
    	}
		catch (SQLException e) {
			throw new ConnectionException("Database connection error!");
		}
    	finally {
    		DBConnector.closeConnection();
    		DBConnector.closeStatements(st);
    	}
    	
    	checker = true;
	}
}
