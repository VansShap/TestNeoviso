package utils;

public class Constants {
	public static final String SQL_CREATE_DB_QUERY = "CREATE DATABASE testneovisodb;";
	public static final String SQL_DROP_DB_IF_EXISTS_QUERY = "DROP DATABASE IF EXISTS testneovisodb;";
	public static final String SQL_CREATE_TABLE_BOOKS = "CREATE TABLE books (id SERIAL PRIMARY KEY, name VARCHAR(40) NOT NULL,  author VARCHAR(20) NOT NULL, description VARCHAR(20) NOT NULL);";
	public static final String SQL_CREATE_TABLE_USERS = "CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR(40) NOT NULL,  pass VARCHAR(20) NOT NULL, cash INTEGER);";
	public static final String SQL_CREATE_TABLE_SHOPS = "CREATE TABLE shops (id SERIAL PRIMARY KEY, name VARCHAR(40) NOT NULL,  cash INTEGER);";
	public static final String SQL_CREATE_TABLE_BOOK_USER = "CREATE TABLE bookUser (id SERIAL PRIMARY KEY, id_user INTEGER, id_book INTEGER, number_of_books INTEGER, FOREIGN KEY (id_user) REFERENCES users (id), FOREIGN KEY (id_book) REFERENCES books (id));";
	public static final String SQL_CREATE_TABLE_BOOK_SHOP = "CREATE TABLE bookShop (id SERIAL PRIMARY KEY, id_shop INTEGER, id_book INTEGER, price INTEGER, number_of_books INTEGER, FOREIGN KEY (id_shop) REFERENCES shops (id), FOREIGN KEY (id_book) REFERENCES books (id));";
	
	public static final String SQL_INSERT_INTO_BOOKS_QUERY = "INSERT INTO books(name, author, description) VALUES ('Fahrenheit 451', 'R. Bradbury', 'fiction'), ('Murder on the Orient Express', 'A. Christie', 'detective'), ('I, Robot', 'I. Asimov', 'ficiton'), ('Advance Grammar in Use', 'B. Hewings', 'education');";
	public static final String SQL_INSERT_INTO_USERS_QUERY = "INSERT INTO users(username, pass, cash) VALUES ('readman', 'br991', 800), ('Headread1', 'abaz7', 900);";
	public static final String SQL_INSERT_INTO_SHOPS_QUERY = "INSERT INTO shops(name, cash) VALUES ('ForReadShop', 1000), ('BookShop', 1500);";
	public static final String SQL_INSERT_INTO_BOOK_SHOP_QUERY = "INSERT INTO bookShop(id_shop, id_book, price, number_of_books) VALUES (1, 1, 95, 2), (1, 2, 87, 2), (1, 3, 102, 2), (1, 4, 120, 2), (2, 1, 115, 3), (2, 2, 107, 3), (2, 3, 122, 3), (2, 4, 140, 3);";
}
