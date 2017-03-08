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
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO meal (user_id, type_meal, date) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, meal.getUser().getId());
			ps.setInt(2, meal.getTypeMeal().getId());
			ps.setDate(3, DateHelper.dateToSql(meal.getDate()));
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

	public List<Food> list(Integer userId) {

		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("SELECT m.date, mf.foodId, mf.quantity, f.name, f.energy, f.protein, f.lipid, f.carbohydrate, f.fiber, f.sugar, f.cholesterol, tm.type FROM meal AS m "+
											"INNER JOIN mealFood AS mf ON m.id = mf.mealId "+
											"INNER JOIN food AS f ON f.id = mf.foodId "+
											"INNER JOIN typeMeal AS tm ON tm.id = m.type_meal " + 
											"WHERE m.user_id = ?");
			ps.setInt(1,userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Food food = new Food();
				food.setName(rs.getString(4)).setEnergy(rs.getDouble(5)).setProtein(rs.getDouble(6))
							.setLipid(rs.getDouble(7)).setCarbohydrate(rs.getDouble(8)).setFiber(rs.getDouble(9))
							.setSugar(rs.getDouble(10)).setCholesterol(rs.getDouble(11));
				String typeMeal = rs.getString(12);
				System.out.println(rs.getDouble(3));
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
}
