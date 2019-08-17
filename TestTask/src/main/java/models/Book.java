package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "author")
	private String author;
	
	@Column (name = "description")
	private String description;
	
	@OneToMany (mappedBy = "book")
	private Set<BookUser> bookUsers;
	
	@OneToMany (mappedBy = "book")
	private Set<BookShop> bookShops;
	
	public Book() {
		
	}
	
	public Book(String name, String author, String description) {
		this.name = name;
		this.author = author;
		this.description = description;
		bookShops = new HashSet<BookShop>();
		bookUsers = new HashSet<BookUser>();
	}

	public void addBookUser(BookUser bu) {
		bu.setBook(this);
		bookUsers.add(bu);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<BookUser> getBookUsers() {
		return bookUsers;
	}

	public void setBookUsers(Set<BookUser> bookUsers) {
		this.bookUsers = bookUsers;
	}

	public Set<BookShop> getBookShops() {
		return bookShops;
	}

	public void setBookShops(Set<BookShop> bookShops) {
		this.bookShops = bookShops;
	}
	
	public String toString() {
		return author + "  " + name + "  " + description;
	}
}
