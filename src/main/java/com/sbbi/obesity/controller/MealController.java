package com.sbbi.obesity.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public boolean saveMeal(@RequestBody SendMeal meal){
		
		MealManager mealManager = new MealManager();
		
		try {
			mealManager.addConenction(ConnectionFactory.getConnection());
			mealManager.post(meal);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			mealManager.close();
		}
		
		return false;
	}
	
	//list meals from a given user
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public List<MealPojo> list(@PathVariable Integer id){
		
	/*@RequestMapping(method = RequestMethod.GET)
	public boolean list(){	
		int userId = 5;*/
		MealManager mealManager = new MealManager();
		List<MealPojo> meal = new ArrayList<MealPojo>();
		
		try {
			mealManager.addConenction(ConnectionFactory.getConnection());
			
			meal = mealManager.list(id);
			
			return meal;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mealManager.close();
		}
		
		return meal;
	}
	
}
