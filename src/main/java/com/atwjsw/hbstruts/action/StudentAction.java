package com.atwjsw.hbstruts.action;

import java.util.List;

import com.atwjsw.hbstruts.entity.Student;
import com.atwjsw.hbstruts.entity.User;
import com.atwjsw.hbstruts.service.StudentDAO;
import com.atwjsw.hbstruts.service.impl.StudentDAOImpl;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends SuperAction implements ModelDriven<Student> {
	
	private Student student = new Student();

	private static final long serialVersionUID = 1L;

	public String query() {
		StudentDAO studentDAO = new StudentDAOImpl();
		List<Student> students = studentDAO.queryAllStudents();
		// if (students!=null&&students.size()>0) {
		request.setAttribute("students_list", students);
		// }
		return "query_success";
	}

	public String delete() {
		StudentDAO studentDAO = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		studentDAO.deleteStudent(sid);
		return "delete_success";
	}
	
	public String add() {
		StudentDAO studentDAO = new StudentDAOImpl();
		studentDAO.addStudent(student);
		return "add_success";
	}
	
	public String modify() {
		StudentDAO studentDAO = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		Student student = studentDAO.queryStudentBySid(sid);
		request.setAttribute("modify_students", student);
		return "modify_success";
	}
	
	public String save() {
		StudentDAO studentDAO = new StudentDAOImpl();
		studentDAO.updateStudent(student);
		return "save_success";
	}

	@Override
	public Student getModel() {
		return this.student;
	}

}
