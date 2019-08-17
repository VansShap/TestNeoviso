package dao;

import models.Book;

public interface BookDAO {
	
	public Book findBookById (int id);
}
