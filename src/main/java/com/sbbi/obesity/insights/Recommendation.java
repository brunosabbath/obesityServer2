package com.sbbi.obesity.insights;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Insight;
import com.sbbi.obesity.model.Meal;

public class Recommendation {

	private Connection connection;
	
	public List<Food> getRecommendation(Insight insight, List<FrequentItems> listFrequentItems, List<Meal> myMealList) {
		
		buildConnection();
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		List<Food> list = dao.list();
		
		List<Food> food = getCandidates(insight, myMealList);
		List<Food> recommendedFood = getRecommendedFood(food, myMealList);
		return recommendedFood;
		
	}

	private List<Food> getRecommendedFood(List<Food> food, List<Meal> myMealList) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Food> getCandidates(Insight insight, List<Meal> myMealList) {
		// TODO Auto-generated method stub
		return null;
	}

	private void buildConnection() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
