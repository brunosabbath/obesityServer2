package com.sbbi.obesity.manager;

import java.sql.Connection;

import com.sbbi.obesity.dao.UserDaoImpl;
import com.sbbi.obesity.model.User;

public class UserManager {

	private final int INVALID = -1;
	private Connection connection;
	
	public UserManager(Connection connection) {
		this.connection = connection;
	}

	public int login(User user) {
		
		UserDaoImpl dao = new UserDaoImpl(connection);
		
		User userAfterLogin = dao.verifyCredentialsAndGetUser(user);
		
		if(isValidUser(userAfterLogin)){
			return userAfterLogin.getId();
		}
		
		return INVALID;
		
	}

	private boolean isValidUser(User userAfterLogin) {
		if(userAfterLogin != null)
			return true;
		else
			return false;
	}

	public int insert(User user) {
		
		int id = INVALID;
		UserDaoImpl dao = new UserDaoImpl(connection);
		boolean emailExists = dao.emailExists(user);
		
		
		if(!emailExists){
			id = dao.insert(user);
		}
		
		return id;
		
	}

	public User getUserById(Integer userId) {
		
		UserDaoImpl dao = new UserDaoImpl(connection);
		
		User user = dao.getById(userId);
		
		return user;
	}

	
	
}
