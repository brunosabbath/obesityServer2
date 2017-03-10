package com.sbbi.obesity.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.MealManager;
import com.sbbi.obesity.model.SendMeal;
import com.sbbi.obesity.model.pojo.MealPojo;


@RestController
@RequestMapping("/meal")
public class MealController {

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
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<MealPojo> list(@PathVariable Integer id){
		
	/*@RequestMapping(method = RequestMethod.GET)
	public boolean list(){	
		int userId = 5;*/
		Connection connection = null;

		try {
			connection = ConnectionFactory.getConnection();
			MealManager mealManager = new MealManager(connection);
			
			List<MealPojo> meal = mealManager.list(id);
			
			return meal;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
}
