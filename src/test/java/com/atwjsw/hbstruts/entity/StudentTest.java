package com.atwjsw.hbstruts.entity;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.atwjsw.hbstruts.db.MyHibernateSessionFactory;
import com.atwjsw.hbstruts.service.StudentDAO;
import com.atwjsw.hbstruts.service.impl.StudentDAOImpl;

public class StudentTest {
	
	/*
	 * 测试通过注解的持久类生成数据库结构。
	 * 需要修改hibernate.cfg.xml来配置映射参考类
	 */
	@Test
	public void testSchemaExport() {
		
		Configuration config = new Configuration().configure();
		//SessionFactory sessionFactory = config.buildSessionFactory();
		
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);		
	}	
	
	/*
	 * 添加学生持久化类测试数据
	 */
	@Test
	public void testAddData() {
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Student stu1 = new Student("S0000001", "张三丰", "男", new Date(), "武当山");
		Student stu2 = new Student("S0000002", "张无忌", "男", new Date(), "广州市");
		Student stu3 = new Student("S0000003", "慕女神", "女", new Date(), "北京市");
		Student stu4 = new Student("S0000004", "小慕慕", "男", new Date(), "上海市");
		session.save(stu1);	
		session.save(stu2);
		session.save(stu3);
		session.save(stu4);
		tx.commit();
	
	}	
	
	@Test
	public void testQueryAllStudents() {
		
		StudentDAO studentDAO = new StudentDAOImpl(); 
		List<Student> students = studentDAO.queryAllStudents(); 
		for (Student s: students) {
			System.out.println(s);
		}		
	}	
	
	@Test
	public void testQueryStudentBySid() {
		
		StudentDAO studentDAO = new StudentDAOImpl(); 
		Student stu1 = studentDAO.queryStudentBySid("S0000001"); 
		Student stu4 = studentDAO.queryStudentBySid("S0000004"); 
		Student stu5 = studentDAO.queryStudentBySid("S0000005"); 
		System.out.println(stu1);
		System.out.println(stu4);		
		System.out.println(stu5);		
	}	
	
	@Test
	public void testAddStudent() {		
		StudentDAO studentDAO = new StudentDAOImpl(); 
		Student stu1 = new Student("S0000005", "大慕慕", "男", new Date(), "西安市");
		System.out.println(studentDAO.addStudent(stu1));
	}
	
	@Test
	public void testDeleteStudent() {		
		StudentDAO studentDAO = new StudentDAOImpl(); 
		//System.out.println(studentDAO.deleteStudent("S0000002"));
		System.out.println(studentDAO.deleteStudent("S0000005"));
	}
	
	@Test
	public void testGetNewSid() {		
		StudentDAOImpl studentDAO = new StudentDAOImpl(); 
		//System.out.println(studentDAO.getNewSid());
	}

}
