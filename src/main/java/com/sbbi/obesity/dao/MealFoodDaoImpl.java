package com.sbbi.obesity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;

public class MealFoodDaoImpl {

	private Connection connection;
	
	public MealFoodDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public void insert(Meal meal, int mealId, double[] quantity) {
		
		try {
			
			for(int i = 0; i < meal.getMealFoods().size(); i++){
				PreparedStatement ps = connection.prepareStatement("INSERT INTO mealFood (mealId, foodId, quantity) VALUES (?,?,?)");
				ps.setInt(1, mealId);
				ps.setInt(2, meal.getMealFoods().get(i).getFood().getId());
				ps.setDouble(3, quantity[i]);
				ps.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
