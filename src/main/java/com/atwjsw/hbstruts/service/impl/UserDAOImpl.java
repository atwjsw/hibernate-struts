package com.atwjsw.hbstruts.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.atwjsw.hbstruts.db.MyHibernateSessionFactory;
import com.atwjsw.hbstruts.entity.User;
import com.atwjsw.hbstruts.service.UserDAO;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean userLogin(User user) {		
		
		String hql = "from User where username=? and password=?";
		//Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Session session = MyHibernateSessionFactory.getSessionFactory().openSession();
		//Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());		
		
		List<User> users = query.list();
		//tx.commit();
		session.close();
		if (users!=null && users.size() > 0) {
			for(User u: users) {
				System.out.println(u);
			}
			return true;
		} else {	
			System.out.println("login failure.");
			return false;
		}		
		
		
	}
}
