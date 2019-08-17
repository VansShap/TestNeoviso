package impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ShopDAO;
import models.Shop;
import models.User;
import utils.HibernateSessionFactory;

public class ShopDAOImpl implements ShopDAO{

	public Shop findShopById(int id) {
		return HibernateSessionFactory.getSessionFactory().openSession().get(Shop.class, id);
	}

	public List<Shop> findAll() {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		List<Shop> list = (List<Shop>) session.createSQLQuery("Select * From shops").addEntity(Shop.class).list();
		tx1.commit();
		session.close();
		return list;
	}

	public void update(Shop shop) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(shop);
		tx1.commit();
		session.close();
	}
	
}
