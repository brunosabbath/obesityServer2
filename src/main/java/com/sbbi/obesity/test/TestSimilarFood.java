package com.sbbi.obesity.test;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbbi.obesity.constants.AteMuchOrLess;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.insights.InsightsMethod;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.manager.MealManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.pojo.FoodPojo;
import com.sbbi.obesity.model.pojo.MealPojo;

public class TestSimilarFood {

	public static List<Meal> myMealList = new ArrayList<Meal>();
	
	public static void main(String[] args) {
		
		buildMealList();
	}

	private static void buildMealList() {
		
		FoodManager foodManager;
		
		try {
			foodManager = new FoodManager(ConnectionFactory.getConnection());
			
			Food apple = foodManager.getFoodByAmount("Apple", 140);
			Food banana = foodManager.getFoodByAmount("Banana", 95);
			Food cookie = foodManager.getFoodByAmount("cookie", 50);
			
			Food rice = foodManager.getFoodByAmount("Rice", 200);
			Food fries = foodManager.getFoodByAmount("french_fries", 100);
			Food pasta = foodManager.getFoodByAmount("pasta", 400);
			
			Food rice2 = foodManager.getFoodByAmount("Rice", 240);
			Food grilled = foodManager.getFoodByAmount("Grilled_chicken_breast", 100);
			Food fries2 = foodManager.getFoodByAmount("french_fries", 150);
			
			Meal breakfast = new Meal();
			breakfast.setDate(new Date(2017, 03, 20));
			breakfast.addFood(apple);
			breakfast.addFood(banana);
			breakfast.addFood(cookie);
			
			Meal lunch = new Meal();
			lunch.setDate(new Date(2017, 03, 20));
			lunch.addFood(rice);
			lunch.addFood(fries);
			lunch.addFood(pasta);
			
			Meal dinner = new Meal();
			dinner.setDate(new Date(2017, 03, 20));
			dinner.addFood(rice2);
			dinner.addFood(grilled);
			dinner.addFood(fries2);
			
			//2days moderately active
			double totalCaloriesOut = 5000;
			//1 day active man
			totalCaloriesOut = 1700;
			
			//myMealList.add(snack1);
			
			myMealList.add(breakfast);
			myMealList.add(lunch);
			myMealList.add(dinner);
			
			
			/*myMealList.add(breakfastBad);
			myMealList.add(lunchBad);
			myMealList.add(dinnerBad);*/
			
			boolean eatingOutside = true;
			
			foodManager.getInsightsAndRecommendation(myMealList, totalCaloriesOut, eatingOutside);
			
			int userId = 5;
			MealManager mealManager = new MealManager(ConnectionFactory.getConnection());
			mealManager.insightSkippedMeal(userId);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Food cloneWithNewWeight(Food food, int weight) {
		Food newFood = new Food(food);
		newFood.changeAmountGrams(weight);
		return newFood;
	}

}