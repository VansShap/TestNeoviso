package impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.BookShopDAO;
import models.Book;
import models.BookShop;
import models.Shop;
import models.User;
import utils.HibernateSessionFactory;

public class BookShopDAOImpl implements BookShopDAO {

	public List<BookShop> findAssortmentOfShop(Shop shop) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria userCriteria = session.createCriteria(BookShop.class);
        userCriteria.add(Restrictions.ge("numberOfBooks", 1));
        List<BookShop> list = userCriteria.add(Restrictions.eq("shop", shop)).list();
        session.close();
        return list;
	}

	public void update(BookShop bs) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(bs);
		tx1.commit();
		session.close();
	}

	public BookShop findBookShopById(Shop shop, Book book) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(BookShop.class);
		criteria.add(Restrictions.eq("shop", shop));
		criteria.add(Restrictions.eq("book", book));
		BookShop bs = (BookShop) criteria.uniqueResult();
		session.close();
		return bs;
	}

}
