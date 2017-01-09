package com.sbbi.obesity.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.MealManager;
import com.sbbi.obesity.model.SendMeal;


@RestController
@RequestMapping("/meal")
public class MealService {

	@RequestMapping(method = RequestMethod.POST)
	public boolean post(@RequestBody SendMeal meal){
		
		Connection connection = null;

		try {
			connection = ConnectionFactory.getConnection();
			MealManager mealManager = new MealManager(connection);
			
			//meal = updateMealWeight(meal);
			
			System.out.println("meal");
			
			mealManager.post(meal);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
