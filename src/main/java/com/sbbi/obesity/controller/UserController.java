package com.sbbi.obesity.controller;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.dao.UserDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.UserManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.User;
import com.sbbi.obesity.response.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	private final int NOT_FOUND = -1;
	
	@RequestMapping(method = RequestMethod.POST, value="/insert")
	public int inseertUser(@RequestBody User user) {

		Connection connection = null;
		int id = NOT_FOUND;
		
		try {
			connection = ConnectionFactory.getConnection();
			UserManager manager = new UserManager(connection);
			id = manager.insert(user);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getById(@PathVariable Integer userId){
		
		UserDaoImpl dao = null;
		Connection connection;
		try {
			connection = ConnectionFactory.getConnection();
			UserManager manager = new UserManager(connection);
			User user = manager.getUserById(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		/*if(foodNotFound(food)){
			return new Response().setData("food not found");
		}
		else{
			return new Response().setData(food);
		}*/
		
		
	}
	
	
}
