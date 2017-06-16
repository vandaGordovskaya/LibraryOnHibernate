package main.java.com.library.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static{
        try{
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println("Initial SessionFactory creation failed. " + e);

            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
