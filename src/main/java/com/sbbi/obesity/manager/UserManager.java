package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sbbi.obesity.dao.UserDaoImpl;
import com.sbbi.obesity.model.User;

public class UserManager {

	private final int INVALID = -1;
	private Connection connection;
	
	public UserManager(Connection connection) {
		this.connection = connection;
	}

	public UserManager() {}

	public User login(User user) {
		
		UserDaoImpl dao = new UserDaoImpl(connection);
		
		User userAfterLogin = dao.verifyCredentialsAndGetUser(user);
		
		if(isValidUser(userAfterLogin)){
			return userAfterLogin;
		}
		
		return null;
		
	}

	private boolean isValidUser(User userAfterLogin) {
		if(userAfterLogin != null)
			return true;
		else
			return false;
	}

	public User insert(User user) {
		
		int id = INVALID;
		UserDaoImpl dao = new UserDaoImpl(connection);
		boolean emailExists = dao.emailExists(user);
		
		
		if(!emailExists){
			id = dao.insert(user);
		}
		
		user.removePassword();
		user.setId(id);
		
		return user;
		
	}

	public User getUserById(Integer userId) {
		
		UserDaoImpl dao = new UserDaoImpl(connection);
		
		User user = dao.getById(userId);
		
		return user;
	}

	public void addConnection(Connection connection) {
		this.connection = connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User updateFinger(User user) {
		
		UserDaoImpl dao = new UserDaoImpl(connection);
		return dao.updateFinger(user);
		
	}
	
}