package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.model.Food;

public class FoodManager {

	private Connection connection;
	
	public FoodManager(Connection connection){
		this.connection = connection;
	}
	
	public Food getFood(String name, double amount){
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		
		Food food = dao.getByName(name);
		
		//System.out.println(food);
		food.changeAmountGrams(amount);
		//System.out.println(food);
		
		return food;
		
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
