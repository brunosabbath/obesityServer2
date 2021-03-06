package com.sbbi.obesity.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.model.User;

public class UserDaoImpl {

	private static final int NOT_FOUND = -1;
	private static final int SUCCESS = 1;
	private static final int FAILURE = -1;
	
	private Connection connection;
	
	public UserDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public int insert(User user){
		
		int id = NOT_FOUND;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, email, password, height, weight, sex) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setDouble(4, user.getHeight());
			ps.setDouble(5, user.getWeight());
			ps.setString(6, String.valueOf(user.getSex()));
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
		
		User userLogin = null;
		
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
	
	public void updateWeight(User user, double newWeight){
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("UPDATE user SET weight = ? WHERE id = ?");
			ps.setDouble(1, newWeight);
			ps.setInt(2, user.getId());
			ps.executeUpdate();
			
				
		} catch (SQLException e){
			e.printStackTrace();
		}
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

	public User updateFinger(User user) {
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("UPDATE user SET finger_height = ?, finger_width = ? WHERE id = ?");
			ps.setDouble(1, user.getFingerLength());
			ps.setDouble(2, user.getFingerWidth());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
			
			return user;
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public User getUserFinger(User user){
		
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("SELECT finger_height, finger_width FROM user WHERE id = ?");
			ps.setInt(1,user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				user.setFingerLength(rs.getDouble(1));
				user.setFingerWidth(rs.getDouble(2));
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return user;
		
	}
	
}
