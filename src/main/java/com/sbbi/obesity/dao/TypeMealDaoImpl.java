package com.sbbi.obesity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.model.TypeMeal;

public class TypeMealDaoImpl {

private Connection conn;
	
	public TypeMealDaoImpl(Connection conn){
		this.conn = conn;
	}
	
	public TypeMeal getById(int id) {
		
		PreparedStatement ps = null;
		
		TypeMeal typeMeal = new TypeMeal();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM typeMeal WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				typeMeal = Builder.buildTypeMeal(rs);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return typeMeal;
		
	}
	
	public TypeMeal getByName(String name) {
		
		PreparedStatement ps = null;
		
		TypeMeal typeMeal = new TypeMeal();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM typeMeal WHERE type = ?");
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				typeMeal = Builder.buildTypeMeal(rs);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return typeMeal;
		
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
