package com.sbbi.obesity.insights;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Insight;
import com.sbbi.obesity.model.Meal;

public class Recommendation {

	private Connection connection;
	
	public List<Food> getRecommendation(Insight insight, List<FrequentItems> listFrequentItems, List<Meal> myMealList, double totalCaloriesIn, double totalCaloriesOut) {
		
		buildConnection();
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		List<Food> list = dao.list();
		
		List<Food> food = getCandidates(insight, myMealList, totalCaloriesIn, totalCaloriesOut);
		//List<Food> recommendedFood = getRecommendedFood(food, myMealList);
		//return recommendedFood;
		return null;
		
	}

	private List<Food> getRecommendedFood(List<Food> food, List<Meal> myMealList) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Food> getCandidates(Insight insight, List<Meal> myMealList, double totalCaloriesIn, double totalCaloriesOut) {
		//algo que va substituir essa ma comida na refeicao: tem q ser uma comida q nao esta presente na refeicao e que va substituir a ma comida 
		//tenho q pegar a porcao de cada comida no prato
		//trneho q pegar comidas que vao deixar minha caloriesIn-caloriesOut = 0 c/ o mesmo amout of food do que a comida ruim
		List<FrequentItems> unhealthyFood = insight.getUnhealthyFood();
		
		try {
			FoodManager manager = new FoodManager(ConnectionFactory.getConnection());
			manager.listFoodBut(unhealthyFood, totalCaloriesIn, totalCaloriesOut);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
