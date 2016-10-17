package com.sbbi.obesity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.helpers.DateHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;

public class MealDaoImpl {

	private static final int NOT_FOUND = -1;
	private Connection connection;
	
	public MealDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	public int insert(Meal meal){

		int id = NOT_FOUND;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO meal (type_meal, date) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, meal.getTypeMeal().getId());
			ps.setDate(2, DateHelper.dateToSql(meal.getDate()));
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				id = rs.getInt(1);
				
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//public List<Meal> getMeal(int userId){
	public List<Meal> getMeal(){
		
		PreparedStatement ps = null;
		
		Food food = new Food();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM food WHERE name = ?");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				food = Builder.buildFood(rs);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
