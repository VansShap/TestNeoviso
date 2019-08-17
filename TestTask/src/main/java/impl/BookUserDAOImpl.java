package impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.BookUserDAO;
import models.Book;
import models.BookUser;
import models.User;
import utils.HibernateSessionFactory;

public class BookUserDAOImpl implements BookUserDAO{

	public List<BookUser> findCollectionOfUser(User user) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria userCriteria = session.createCriteria(BookUser.class);
        List<BookUser> list = userCriteria.add(Restrictions.eq("user", user)).list();
        session.close();
        return list;
	}

	public void update(BookUser bu) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(bu);
		tx1.commit();
		session.close();
	}
	
    public void save(BookUser bu) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(bu);
        tx1.commit();
        session.close();
    }

	public BookUser findBookUserById(Book book, User user) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(BookUser.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("book", book));
		BookUser bu = (BookUser) criteria.uniqueResult();
		session.close();
		return bu;
	}

}
