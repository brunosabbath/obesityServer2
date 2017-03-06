package com.sbbi.obesity.controller;

import java.sql.Connection;
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

	private final int NOT_FOUND = -1;
	
	@RequestMapping(method = RequestMethod.POST)
	public User login(@RequestBody User user) {

		Connection connection = null;
		int id = NOT_FOUND;
		
		try {
			connection = ConnectionFactory.getConnection();
			UserManager manager = new UserManager(connection);
			User loggedInUser = manager.login(user);
			
			return loggedInUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
