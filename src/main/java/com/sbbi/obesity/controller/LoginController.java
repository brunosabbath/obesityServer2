package com.sbbi.obesity.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.UserManager;
import com.sbbi.obesity.model.User;

@RestController
@RequestMapping("/login")
public class LoginController {

	
	@RequestMapping(method = RequestMethod.POST)
	public User login(@RequestBody User user) {

		try {
			UserManager manager = new UserManager(ConnectionFactory.getConnection());
			User loggedInUser = manager.login(user);
			
			return loggedInUser;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
