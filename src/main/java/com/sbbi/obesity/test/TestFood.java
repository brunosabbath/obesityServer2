package com.sbbi.obesity.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.Food;


public class TestFood {

	public static void main(String[] args) {
		
		Connection connection;
		
		try {
			FoodDaoImpl dao = new FoodDaoImpl(ConnectionFactory.getConnection());
			
			Food food = dao.getByName("apple");
			
			System.out.println(food);
			
			food.changeAmountGrams(150);
			
			System.out.println(food);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
