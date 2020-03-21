package br.com.mdsysfolha.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	public static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session currentSession() {   
		Session session = (Session) threadLocal.get();   
		// Open a new Session, if this Thread has none yet   
		if (session == null) {
			try {
				session = sessionFactory.openSession();
			} catch (HibernateException e) {
				System.err.println("Failed trying to open hibernate session." + e.getMessage());
			}   
			threadLocal.set(session);
		}
		return session;   
	}
	
	public static void closeSession() {   
		Session s = (Session) threadLocal.get();   
		if (s != null)  
			try {
				s.close();
			} catch (HibernateException e) {
				System.err.println("Failed trying to close hibernate session." + e);
			}
		threadLocal.set(null);   
	}
	
	public static void clearSession() {   
		Session s = (Session) threadLocal.get();   
		if (s != null)  
			try {
				s.clear();
			} catch (HibernateException e) {
				System.err.println("Failed trying to close hibernate session." + e);
			}
		threadLocal.set(null);   
	}

	
}
