package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "username")
	private String username;
	
	@Column (name = "pass")
	private String pass;
	
	@Column (name = "cash")
	private int cash;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy = "user")
	private Set<BookUser> bookUsers;
	
	public User() {
		
	}
	
	public User(String username, String pass, int cash) {
		this.username = username;
		this.pass = pass;
		this.cash = cash;
		bookUsers = new HashSet<BookUser>();
	}

	public void addBookUser(BookUser bu) {
		bu.setUser(this);
		bookUsers.add(bu);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public Set<BookUser> getBookUsers() {
		return bookUsers;
	}

	public void setBookUsers(Set<BookUser> bookUsers) {
		this.bookUsers = bookUsers;
	}
}
