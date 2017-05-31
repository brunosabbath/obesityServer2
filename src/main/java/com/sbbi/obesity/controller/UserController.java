package com.sbbi.obesity.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.UserManager;
import com.sbbi.obesity.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(method = RequestMethod.POST, value="/signup")
	public User signUp(@RequestBody User user) {

		UserManager manager = new UserManager();
		
		try {
			manager.addConnection(ConnectionFactory.getConnection());
			user = manager.insert(user);

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			manager.close();
		}
		
		return user;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Integer userId){
		
		UserManager manager = new UserManager();
		User user = new User();
		
		try {
			manager.addConnection(ConnectionFactory.getConnection());
			user = manager.getUserById(userId);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			manager.close();
		}
		
		return user;
		
	}
	
}