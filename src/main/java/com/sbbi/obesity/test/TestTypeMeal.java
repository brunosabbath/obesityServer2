package com.sbbi.obesity.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.sbbi.obesity.dao.TypeMealDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.TypeMeal;

public class TestTypeMeal {

public static void main(String[] args) {
		
		TypeMealDaoImpl dao = null;
	
		try {
			dao = new TypeMealDaoImpl(ConnectionFactory.getConnection());
			
			TypeMeal meal = dao.getByName("Lunch");
			System.out.println(meal);
			
			meal = dao.getById(1);
			System.out.println(meal);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
	}
	
}
