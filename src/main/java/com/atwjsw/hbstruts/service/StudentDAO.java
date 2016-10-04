package com.atwjsw.hbstruts.service;

import java.util.List;

import com.atwjsw.hbstruts.entity.Student;

/*
 * 管理学生数据的业务逻辑接口
 */
public interface StudentDAO {
	
	//查询所有学生资料
	public List<Student> queryAllStudents();
	
	//根据学号查询学生资料
	public Student queryStudentBySid(String sid);
	
	//增加学生记录
	public boolean addStudent(Student s);
	
	//修改学生信息
	public boolean updateStudent(Student s);
	
	//删除学生记录
	public boolean deleteStudent(String sid);
	

}
