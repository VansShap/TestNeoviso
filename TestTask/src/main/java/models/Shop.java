package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="shops")
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "cash")
	private int cash;
	
	@OneToMany (mappedBy = "shop")
	private Set<BookShop> bookShops;
	
	public Shop() {
		
	}
	
	public Shop(String name, int cash) {
		this.name = name;
		this.cash = cash;
		bookShops = new HashSet<BookShop>();
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

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public Set<BookShop> getBookShops() {
		return bookShops;
	}

	public void setBookShops(Set<BookShop> bookShops) {
		this.bookShops = bookShops;
	}
	
	public String toString() {
		return name;
	}
}
