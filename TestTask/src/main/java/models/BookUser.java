package models;

import javax.persistence.*;

@Entity
@Table(name="bookUser")
public class BookUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_user")
	private User user;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_book")
	private Book book;
	
	@Column (name = "number_of_books")
	private int numberOfBooks;
	
	public BookUser() {
		
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
}
