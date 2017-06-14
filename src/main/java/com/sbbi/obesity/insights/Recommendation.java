package com.sbbi.obesity.insights;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Insight;
import com.sbbi.obesity.model.Meal;

public class Recommendation {

	private Connection connection;
	
	public List<String> getRecommendation(Insight insight, List<FrequentItems> listFrequentItems, List<Meal> myMealList, double totalCaloriesIn, double totalCaloriesOut) {
		
		buildConnection();
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		List<Food> list = dao.list();
		
		List<String> listResult = getCandidates(insight, myMealList, totalCaloriesIn, totalCaloriesOut);
		//List<Food> recommendedFood = getRecommendedFood(food, myMealList);
		//return recommendedFood;
		return listResult;
		
	}

	/**
	 * algo que va substituir essa ma comida na refeicao: tem q ser uma comida q nao esta presente na refeicao e que va substituir a ma comida
	 * tenho q pegar a porcao de cada comida no prato
	 * teneho q pegar comidas que vao deixar minha caloriesIn-caloriesOut = 0 c/ o mesmo amout of food do que a comida ruim
	 */
	private List<String> getCandidates(Insight insight, List<Meal> myMealList, double totalCaloriesIn, double totalCaloriesOut) {
		List<FrequentItems> unhealthyFood = insight.getUnhealthyFood();
		List<String> finalResult = new ArrayList<String>();
		
		try {
			FoodManager manager = new FoodManager(ConnectionFactory.getConnection());
			manager.setFrequentItems(insight.getFrequentFood());
			manager.listFoodBut(unhealthyFood, totalCaloriesIn, totalCaloriesOut);
			
			finalResult.add(manager.getFinalInsight());
			finalResult.add(manager.getSubstitution());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return finalResult;
	}

	private void buildConnection() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static String adjustAmountUnhealthyFoodToBecomeOk(List<FrequentItems> unhealthyFood, double totalCaloriesIn, double totalCaloriesOut) {
		
		StringBuilder string = new StringBuilder();
		
		double differenceInAndOut = totalCaloriesIn - totalCaloriesOut;
		
		//bad foods that can be ok if you reduce it
		for(FrequentItems frequentItems : unhealthyFood){
			double caloriesWithouthFood = totalCaloriesIn - frequentItems.getCalories();
			double difference = totalCaloriesIn - caloriesWithouthFood;
			double canEat = totalCaloriesOut - caloriesWithouthFood;
			double pct = canEat * 100 / totalCaloriesIn;
			string.append("You still can eat " + frequentItems.getName() + " if you reduce it to " + pct + "%.\n");
			//double pct = food.getEnergy() * 100 / totalCaloriesIn;
		}
		
		return string.toString();
	}

	//Filter food. If food in same quantity as unhealthy food but with less calories than unhealthy food, get it 
	public static Set<String> getOnlyGoodFood(List<FrequentItems> unhealthyFood, List<Food> listFood, double totalCaloriesIn, double totalCaloriesOut) {
		
		Set<String> setBestFood = new HashSet<String>();
		
		for(FrequentItems badFood : unhealthyFood){
			
			double caloriesInWithoutBadFood = totalCaloriesIn - badFood.getCalories();
			
			for(Food goodFood : listFood){
				
				goodFood.changeAmountGrams(badFood.getGrams());
				
				double deficit = (caloriesInWithoutBadFood + goodFood.getEnergy()) - totalCaloriesOut; 
				
				if(goodFood.getEnergy() < badFood.getCalories() && deficit <= 0){
					setBestFood.add(goodFood.getName());
				}
			}
		}
		
		return setBestFood;
	}

}
