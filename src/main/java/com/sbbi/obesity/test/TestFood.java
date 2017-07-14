package com.sbbi.obesity.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.Food;


public class TestFood {

	public static void main(String[] args) {
		
		try {
			FoodDaoImpl dao = new FoodDaoImpl(ConnectionFactory.getConnection());
			
			Food food = dao.getByName("french_fries");
			
			System.out.println(food);
			
			food.changeAmountGrams(80);
			
			System.out.println(food);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
