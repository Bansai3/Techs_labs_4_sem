package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Session factory creation failed!");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory GetSessionFactory() {
        return sessionFactory;
    }

    public static void ShutDown() {
        sessionFactory.close();
    }

}
