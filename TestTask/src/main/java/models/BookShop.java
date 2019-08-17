package models;

import javax.persistence.*;

@Entity
@Table(name="bookShop")
public class BookShop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_shop")
	private Shop shop;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_book")
	private Book book;
	
	@Column (name = "price")
	private int price;
	
	@Column (name = "number_of_books")
	private int numberOfBooks;
	
	public BookShop() {
		
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
}
