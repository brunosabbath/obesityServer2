package com.sbbi.obesity.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.insights.Insights;
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

		int userId = 5;
		
		buildMealList();
		
		/*try {
			
			MealManager mealManager = new MealManager(ConnectionFactory.getConnection());
			
			List<MealPojo> mealList = mealManager.list(userId);
			
			for(MealPojo meal : mealList){
				if(hasFood(meal.getListFood().size())){
					System.out.println(meal.getType());
					printFoodMeal(meal.getListFood());
					System.out.println();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
	}

	private static void buildMealList() {
		
		FoodManager foodManager;
		try {
			foodManager = new FoodManager(ConnectionFactory.getConnection());
			
			Food apple = foodManager.getFood("Apple", 100);
			Food banana = foodManager.getFood("Banana", 118);
			Food chicken = foodManager.getFood("Grilled chicken breast", 100);
			Food rice = foodManager.getFood("Rice", 100);
			Food bread = foodManager.getFood("Sandwich bread", 100);
			Food chips = foodManager.getFood("Chips", 100);
			Food carrot = foodManager.getFood("Carrot", 100);
			Food grape = foodManager.getFood("Grape", 100);
			
			Meal breakfastOk = new Meal();
			breakfastOk.addFood(apple);
			breakfastOk.addFood(banana);
			breakfastOk.addFood(apple);
			
			Meal breakfastBad = new Meal();
			breakfastBad.addFood(bread.changeAmountGrams(300));
			breakfastBad.addFood(banana);
			breakfastBad.addFood(apple);
			
			Meal lunchOk = new Meal();
			lunchOk.addFood(cloneWithNewWeight(chips, 30));
			lunchOk.addFood(cloneWithNewWeight(rice, 150));
			lunchOk.addFood(cloneWithNewWeight(carrot, 70));
			lunchOk.addFood(cloneWithNewWeight(chicken, 300));
			
			Meal lunchBad = new Meal();
			lunchBad.addFood(cloneWithNewWeight(chips, 100));
			lunchBad.addFood(cloneWithNewWeight(rice, 300));
			lunchBad.addFood(cloneWithNewWeight(carrot, 40));
			lunchBad.addFood(cloneWithNewWeight(chicken, 100));
			
			Meal dinnerOk = new Meal();
			dinnerOk.addFood(cloneWithNewWeight(rice, 180));
			dinnerOk.addFood(cloneWithNewWeight(carrot, 70));
			dinnerOk.addFood(cloneWithNewWeight(chicken, 100));
			dinnerOk.addFood(cloneWithNewWeight(banana, 150));
			
			Meal dinnerBad = new Meal();
			dinnerBad.addFood(cloneWithNewWeight(rice, 100));
			dinnerBad.addFood(cloneWithNewWeight(chicken, 150));
			dinnerBad.addFood(cloneWithNewWeight(bread, 300));
			
			Meal snack1 = new Meal();
			snack1.addFood(cloneWithNewWeight(apple, 150));
			snack1.addFood(cloneWithNewWeight(banana, 175));
			
			Meal dinner = new Meal();
			dinner.addFood(cloneWithNewWeight(rice, 100));
			dinner.addFood(cloneWithNewWeight(chips, 250));
			
			double totalCaloriesOut = 4000;
			
			myMealList.add(snack1);
			myMealList.add(dinner);
			myMealList.add(breakfastOk);
			myMealList.add(breakfastBad);
			myMealList.add(lunchOk);
			myMealList.add(lunchBad);
			myMealList.add(dinnerOk);
			myMealList.add(dinnerBad);
			
			List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);
			
			double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
			
			System.out.println("Total calories in: " + totalCaloriesIn);
			
			Insights.ateMuchOrLess(totalCaloriesIn, totalCaloriesOut);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static Food cloneWithNewWeight(Food food, int weight) {

		Food newFood = food;
		
		newFood.changeAmountGrams(weight);
		
		return newFood;
	}

	private static void printFoodMeal(List<FoodPojo> listFood) {
		for(FoodPojo food : listFood)
			System.out.println(food);
		
	}

	private static boolean hasFood(int size) {
		boolean hasFood = false;
		
		if(size > 0)
			hasFood = true;
			
		return hasFood;
	}
	
}