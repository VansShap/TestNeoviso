package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import models.Book;
import models.BookShop;
import models.BookUser;
import models.Shop;
import models.User;

public class HibernateSessionFactory {
	private static SessionFactory sessionFactory;
	
    private HibernateSessionFactory() {}
    
    public static SessionFactory getSessionFactory() {
    
    	if (DBCreator.getChecker() == false) {
    		DBCreator.deleteDB();
    		DBCreator.createDB();
    	}
    	
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Shop.class);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(BookShop.class);
                configuration.addAnnotatedClass(BookUser.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.err.println("Hibernate SessionFactory error.");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
