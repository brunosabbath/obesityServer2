package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbbi.obesity.constants.AteMuchOrLess;
import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.insights.InsightsMethod;
import com.sbbi.obesity.insights.Recommendation;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Meal;

public class FoodManager {

	private Connection connection;
	private List<FrequentItems> listFrequentFoodItem;
	
	public FoodManager(Connection connection){
		this.connection = connection;
		this.listFrequentFoodItem = new ArrayList<FrequentItems>();
	}
	
	public Food getFood(String name, double amount){
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		
		Food food = dao.getByName(name);
		food.changeAmountGrams(amount);
		
		return food;
	}
	
	public void listFoodBut(List<FrequentItems> unhealthyFood, double totalCaloriesIn, double totalCaloriesOut) {
		
		FoodDaoImpl dao = new FoodDaoImpl(connection);
		
		if(!hasUnhealthyFood(unhealthyFood)){
			System.out.println("no unhealthy food");
			unhealthyFood = getTopCandidatesForUnhealthyFoodFromFrequentItems(getFrequentItems());
		}
		
		List<Food> listHealthyFood = dao.listFoodBut(unhealthyFood);
		
		Set<String> goodFood = Recommendation.getOnlyGoodFood(unhealthyFood, listHealthyFood, totalCaloriesIn, totalCaloriesOut);
		System.out.println("You can substitute " + printBadFood(unhealthyFood) + "for " + goodFood);
		
		String recommendationInsight = Recommendation.adjunstAmountUnhealthyFoodToBecomeOk(unhealthyFood, totalCaloriesIn, totalCaloriesOut);
		System.out.println(recommendationInsight);
		
	}

	private String printBadFood(List<FrequentItems> unhealthyFood) {
		StringBuilder string = new StringBuilder();
		
		for(FrequentItems items : unhealthyFood){
			string.append(items.getName() + " ");
		}
		
		return string.toString();
	}

	private List<FrequentItems> getTopCandidatesForUnhealthyFoodFromFrequentItems(List<FrequentItems> frequentItems) {
		
		List<FrequentItems> list = new ArrayList<FrequentItems>();
		list.add(frequentItems.get(0));
		list.add(frequentItems.get(1));
		
		return list;
	}

	private boolean hasUnhealthyFood(List<FrequentItems> unhealthyFood) {
		if(unhealthyFood.isEmpty())
			return false;
		
		return true;
	}

	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<FrequentItems> getFrequentItems(){
		return listFrequentFoodItem;
	}
	
	public void setFrequentItems(List<FrequentItems> frequentFood) {
		this.listFrequentFoodItem = frequentFood;;
	}

	public void getInsightsAndRecommendation(List<Meal> myMealList, double totalCaloriesOut) {
		
		List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);
		
		double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
		
		System.out.println("Total calories in: " + totalCaloriesIn);
		
		AteMuchOrLess ateMuchOrLess = InsightsMethod.ateMuchOrLess(totalCaloriesIn, totalCaloriesOut);
		
		if("TOO_MUCH".equals(ateMuchOrLess.name())){
			//Insights.day(myMealList, totalCaloriesOut);
			InsightsMethod.week(myMealList, totalCaloriesOut);
		}
		
	}
}