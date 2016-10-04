package com.atwjsw.hbstruts.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory {

	private static SessionFactory sessionFactory;

	private MyHibernateSessionFactory() {

	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			Configuration config = new Configuration().configure();
			sessionFactory = config.buildSessionFactory();
		}
		return sessionFactory;
	}

}
