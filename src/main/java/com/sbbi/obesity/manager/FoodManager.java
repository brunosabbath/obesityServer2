package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;

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
	
	public void listFoodBut(List<FrequentItems> unhealthyFood, double totalCaloriesIn, double totalCaloriesOut) {
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		List<Food> listFood = dao.listFoodBut(unhealthyFood);
		
		Set<String> goodFood = getOnlyGoodFood(unhealthyFood, listFood, totalCaloriesIn, totalCaloriesOut);
		
	}
	
	//Filter food. If food in same quantity as unhealthy food but with less calories than unhealthy food, get it 
	private Set<String> getOnlyGoodFood(List<FrequentItems> unhealthyFood, List<Food> listFood, double totalCaloriesIn, double totalCaloriesOut) {
		
		Set<String> setBestFood = new HashSet<String>();
		
		for(FrequentItems badFood : unhealthyFood){
			
			double caloriesIn = totalCaloriesIn - badFood.getCalories();
			
			for(Food goodFood : listFood){
				
				goodFood.changeAmountGrams(badFood.getGrams());
				
				double deficit = (caloriesIn + goodFood.getEnergy()) - totalCaloriesOut; 
				
				if(goodFood.getEnergy() < badFood.getCalories() && deficit <= 0){
					setBestFood.add(goodFood.getName());
				}
				
			}
		}
		
		return setBestFood;
	}

	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}