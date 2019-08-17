import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.BookDAO;
import dao.BookShopDAO;
import dao.BookUserDAO;
import dao.ShopDAO;
import dao.UserDAO;
import impl.BookDAOImpl;
import impl.BookShopDAOImpl;
import impl.BookUserDAOImpl;
import impl.ShopDAOImpl;
import impl.UserDAOImpl;
import models.Book;
import models.BookShop;
import models.BookUser;
import models.Shop;
import models.User;

public class Runner {
	
	public static User authUser = null;
	public static Shop selectedShop = null;
	public static Book selectedBook = null;
	
	public static List<String> authorization() {
		Scanner in = new Scanner(System.in);
		List<String> str = new ArrayList<String>();
		
		System.out.print("Enter username: ");
		String username = in.nextLine();
		str.add(username);
		
		System.out.print("Enter password: ");
		String password = in.nextLine();
		str.add(password);
		
		return str;
	}
	
	public static int enterNumber() {
		Scanner in = new Scanner(System.in);
		System.out.println("Your select: ");
		int number = 0;
		try {
			number = Integer.parseInt(in.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Parsing error. Try again!");
			enterNumber();
		}
		return number;
	}
	
	public static String enterCommand() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
	
	
	public static Shop selectShop() {
		ShopDAO shopImpl = new ShopDAOImpl();
		
		List<Shop> list = shopImpl.findAll();
		
		int counter = 1;
		
		System.out.println("Select a shop:");
		Iterator<Shop> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(counter + ". " + iter.next().toString());
			counter++;
		}
		
		int choose;
		choose = enterNumber();
		while (choose <= 0 || choose > list.size()) {
			System.out.println("Error is occured. Try again!");
			choose = enterNumber();
		}
		
		return list.get(choose-1);
	}
	
	public static void printAssortmentOfShop(Map<Integer, Book> mapBook) {
		for (Map.Entry<Integer, Book> entry : mapBook.entrySet()) {
			int key = entry.getKey();
			Book value = entry.getValue();
			System.out.println(key + ". " + value.toString());
		}
	}
	
	public static Map<Integer, Book> getAssortmentOfShop() {
		BookShopDAO bookShopImpl = new BookShopDAOImpl();
		BookDAO bookImpl = new BookDAOImpl();
		List<BookShop> listBS = bookShopImpl.findAssortmentOfShop(selectedShop);
		Map<Integer, Book> mapBook = new HashMap<Integer, Book>();
		int counter = 1;
		Iterator<BookShop> iter = listBS.iterator();
		while(iter.hasNext()) {
			mapBook.put(counter, bookImpl.findBookById(iter.next().getBook().getId()));
			counter++;
		}
		return mapBook;
	}
	
	public static void buyABook(Map<Integer, Book> mapBook, int number) {
		selectedBook = mapBook.get(number);
		
		BookShopDAO bookShopImpl = new BookShopDAOImpl();
		BookUserDAO bookUserImpl = new BookUserDAOImpl();
		ShopDAO shopImpl = new ShopDAOImpl();
		UserDAO userImpl = new UserDAOImpl();
		
		BookShop bs = bookShopImpl.findBookShopById(selectedShop, selectedBook);
		BookUser bu = null;
		
		int number_of_books = bs.getNumberOfBooks()-1;
		int shop_cash = selectedShop.getCash() + bs.getPrice();
		int user_cash = authUser.getCash() - bs.getPrice();
		
		if (user_cash >= 0) {
			bs.setNumberOfBooks(number_of_books);
			bookShopImpl.update(bs);
			
			selectedShop.setCash(shop_cash);
			shopImpl.update(selectedShop);
			
			authUser.setCash(user_cash);
			bu = bookUserImpl.findBookUserById(selectedBook, authUser);
			if (bu == null) {
				bu = new BookUser();
				bu.setBook(selectedBook);
				bu.setUser(authUser);
				bu.setNumberOfBooks(1);
				bookUserImpl.save(bu);
			}
			else {
				int number_of_user_collection = bu.getNumberOfBooks() + 1;
				bu.setNumberOfBooks(number_of_user_collection);
				bookUserImpl.update(bu);
			}
			userImpl.update(authUser);
			
			System.out.println("Would you like to continue? Press 'y' to continue else press another button to close.");
			String answer = enterCommand();
			if (answer.equals("y")) {
				shopMenu();
			}
		}
		else {
			System.out.println("You don't have enough cash. Sorry");
		}
		
	}
	
	public static void shopMenu() {
		String command = null;
		int number;
		Map<Integer, Book> mapBook = getAssortmentOfShop();
		printAssortmentOfShop(mapBook);
		while (command == null || command != "exit") {
			System.out.println("Your choice: ");
			command = enterCommand();
			if (command.equals("shop") && !command.equals("exit")) {
				selectedShop = selectShop();
				shopMenu();
			}
			else {
				try {
					if (!command.equals("exit")) {
						number = Integer.parseInt(command);
						buyABook(mapBook, number);
						command = "exit";
					}
					else if (command.equals("exit")){
						break;
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Input error. Try again!");
					command = enterCommand();
				}
			}
				
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Good Day! You need to authorize.");
		
		UserDAO userImpl = new UserDAOImpl();
		
		List<String> logAndPass = authorization();
		authUser = userImpl.findUserByLoginAndPass(logAndPass.get(0), logAndPass.get(1));
		
		while(authUser == null) {
			System.out.println("Authorization is failed. Try again!");
			logAndPass = authorization();
			authUser = userImpl.findUserByLoginAndPass(logAndPass.get(0), logAndPass.get(1));
		}
		
		System.out.println("You Authorization is done. Gongrantulations!");
		
		selectedShop = selectShop();
		
		System.out.println("Welcome, you can select a book now. Print the number of interested book. You can also print 'shop' to choose another shop and you can print 'exit' to finish the work.");
		shopMenu();
		System.out.println("See you next time!");
	}

}
