package com.atwjsw.hbstruts.entity;

import org.junit.Assert;
import org.junit.Test;

import com.atwjsw.hbstruts.service.UserDAO;
import com.atwjsw.hbstruts.service.impl.UserDAOImpl;

public class UserTest {
	
	@Test
	public void testUserLogin() {
		
		User user1 = new User("zhangshan", "123456");
		User user2 = new User("lisi", "123456");
		UserDAO userDAO = new UserDAOImpl(); 
		Assert.assertEquals(true, userDAO.userLogin(user1));
		Assert.assertEquals(false, userDAO.userLogin(user2));
		
	}

}
