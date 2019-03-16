package SoftwareDev.ExMAp.DAO;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
 private static final SessionFactory sessionFactory = buildSessionFactory();

 private static SessionFactory buildSessionFactory() {
   try {
     /* It will Create the SessionFactory from hibernate.cfg.xml */
     return new Configuration().configure().buildSessionFactory();
   } catch (Throwable ex) {
     System.err.println("Initial SessionFactory creation failed." + ex);
     throw new ExceptionInInitializerError(ex);
   }
 }

 public static SessionFactory getSessionFactory() {
   return sessionFactory;
 }
} 
