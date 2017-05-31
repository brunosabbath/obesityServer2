package com.sbbi.obesity.controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.response.Response;

@RestController
@RequestMapping("/food")
public class FoodController {
	
	//ping
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public Response ping(){
		return new Response().setData("ping!!! hello :D");
	}
	
	//returns all food items in the database
	@RequestMapping(method = RequestMethod.GET)
	public List<Food> list(){
		
		List<Food> list = new ArrayList<>();
		FoodManager manager = new FoodManager();
		
		try {
			manager.addConnection(ConnectionFactory.getConnection());
			list = manager.listFood();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			manager.close();
		}
		
		return list;
	}
	
	//get food by name
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Response getByName(@PathVariable String name){
		
		FoodManager manager = new FoodManager();
		Response response = new Response();
		
		try {
			
			manager.addConnection(ConnectionFactory.getConnection());
			Food food = manager.getFood(name);
			
			if(food == null){
				response = new Response().setData("food not found");
			}
			else{
				response = new Response().setData(food);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		
		return response;
		
	}
	
	
	
	
}
