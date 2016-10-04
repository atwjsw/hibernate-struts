package com.atwjsw.hbstruts.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.junit.Assert;

import com.atwjsw.hbstruts.entity.User;
import com.atwjsw.hbstruts.service.UserDAO;
import com.atwjsw.hbstruts.service.impl.UserDAOImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * Struts2三种接收表单数据的方式
 * 1. 普通属性
 * 2. 领域对象
 * 3. 模型驱动 （本案例采用）
 */
public class UserAction extends SuperAction implements ModelDriven<User> {
	
	private User user = new User();
	
	@Override
	public User getModel() {
		return this.user;
	}
	
	public String login() {
		
		UserDAO userDAO = new UserDAOImpl(); 
		if (userDAO.userLogin(user)) {
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";			
		} else {
			return "login_failure";
		}		
	}
	
	@SkipValidation
	public String logout() {
		
		System.out.println("User.logout");
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");			
		}
		return "logout_success";
	}

	@Override
	public void validate() {
		
		System.out.println("User.validate");
		if (user.getUsername().trim().equals("")) {
			this.addFieldError("usernameError", "用户名不能为空");
		}		
		if (user.getPassword().length()<6) {
			this.addFieldError("passwordError", "密码长度不能少于6位");
		}
	}	
}
