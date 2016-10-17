package com.sbbi.obesity.service;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.response.Response;

@RestController
@RequestMapping("/food")
public class FoodService {
	
	private static final int NO_ID = 0;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public Response ping(){
		return new Response().setData("ping!!! hello :D");
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Response getByName(@PathVariable String name){
		
		FoodDaoImpl dao = null;
		Food food = new Food();
		
		try {
			dao = new FoodDaoImpl(ConnectionFactory.getConnection());
			food = dao.getByName(name);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		if(foodNotFound(food)){
			return new Response().setData("food not found");
		}
		else{
			return new Response().setData(food);
		}
		
		
	}

	private boolean foodNotFound(Food food) {
		if(food.getId() == NO_ID){
			return true;
		}
		return false;
	}
	
}
