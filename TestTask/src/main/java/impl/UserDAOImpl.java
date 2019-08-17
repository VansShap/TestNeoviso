package impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.UserDAO;
import models.User;
import utils.HibernateSessionFactory;

public class UserDAOImpl implements UserDAO {

	public User findUserByLoginAndPass(String username, String pass) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("pass", pass));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	public User findUserById(int id) {
		return HibernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
	}

	public void update(User user) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(user);
		tx1.commit();
		session.close();
	}
	
}
