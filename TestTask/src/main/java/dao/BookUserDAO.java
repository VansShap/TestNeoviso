package dao;

import java.util.List;

import models.Book;
import models.BookUser;
import models.User;

public interface BookUserDAO {
	
	public List<BookUser> findCollectionOfUser(User user);
	
	public void update(BookUser bu);
	
	public void save(BookUser bu);
	
	public BookUser findBookUserById(Book book, User user);
}
