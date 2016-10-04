package com.atwjsw.hbstruts.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.atwjsw.hbstruts.db.MyHibernateSessionFactory;
import com.atwjsw.hbstruts.entity.Student;
import com.atwjsw.hbstruts.entity.User;
import com.atwjsw.hbstruts.service.StudentDAO;

/*
 * 管理学生数据的业务逻辑实现类
 */
public class StudentDAOImpl implements StudentDAO {

	@Override
	public List<Student> queryAllStudents() {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		String hql = "from Student";
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		tx.commit();
		/*
		 * for (Student s: students) { System.out.println(s); }
		 */
		return students;

	}

	@Override
	public Student queryStudentBySid(String sid) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Student student = (Student) session.get(Student.class, sid);
		// System.out.println(student);
		tx.commit();
		return student;
	}

	@Override
	public boolean addStudent(Student s) {
		
		s.setSid(this.getNewSid());
		Session session =  null;
		Transaction tx = null;
		try {
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} 
		return true;
		
	}

	@Override
	public boolean updateStudent(Student s) {
		
		Session session =  null;
		Transaction tx = null;
		try {
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} 
		return true;
	}

	@Override
	public boolean deleteStudent(String sid) {
		Session session =  null;
		Transaction tx = null;
		try {
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, sid);
			session.delete(student);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} 
		return true;
	}
	
	private String getNewSid() {
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		String hql = "select max(sid) from Student";
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		String maxid = (String)query.uniqueResult();
		tx.commit();
		
		if (maxid==null || maxid.length()== 0) {
			return "S0000001";
		}
		
		int i = Integer.valueOf(maxid.substring(1)); //返回后7位的数值
		i++;		
		String zeros = "0000000".substring(String.valueOf(i).length());
		return "S"+ zeros + String.valueOf(i);		
	}

}
