package impl;

import dao.BookDAO;
import models.Book;
import utils.HibernateSessionFactory;

public class BookDAOImpl implements BookDAO{

	public Book findBookById(int id) {
		return HibernateSessionFactory.getSessionFactory().openSession().get(Book.class, id);
	}

}
