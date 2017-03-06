package com.sbbi.obesity.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.model.User;

public class UserDaoImpl {

	private static final int NOT_FOUND = -1;
	private Connection connection;
	
	public UserDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public int insert(User user){
		
		int id = NOT_FOUND;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, email, password) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				id = rs.getInt(1);
				
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public User verifyCredentialsAndGetUser(User user){
		
		PreparedStatement ps = null;
		
		User userLogin = new User();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				userLogin = Builder.buildUser(rs);
				
			}
			
			return userLogin;
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean emailExists(User user) {
		
		boolean answer = false;
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
			ps.setString(1, user.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				answer = true;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return answer;
	}

	public User getById(Integer userId) {
		
		PreparedStatement ps = null;
		User user = null;
		
		try {
			ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
			ps.setInt(1,userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				user = Builder.buildUser(rs);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return user;
	}
	
}
