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
			Food banana = foodManager.getFood("Banana", 100);
			Food chicken = foodManager.getFood("Grilled chicken breast", 100);
			Food rice = foodManager.getFood("Rice", 100);
			Food bread = foodManager.getFood("Sandwich bread", 100);
			Food chips = foodManager.getFood("Chips", 100);
			Food carrot = foodManager.getFood("Carrot", 100);
			Food grape = foodManager.getFood("Grape", 100);
			
			Meal breakfastOk = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 20));
			breakfastOk.addFood(apple);
			breakfastOk.addFood(banana);
			breakfastOk.addFood(apple);
			
			Meal breakfastBad = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 21));
			breakfastBad.addFood(cloneWithNewWeight(bread, 100));
			breakfastBad.addFood(banana);
			breakfastBad.addFood(apple);
			
			Meal lunchOk = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 20));
			lunchOk.addFood(cloneWithNewWeight(chips, 160));
			lunchOk.addFood(cloneWithNewWeight(rice, 150));
			lunchOk.addFood(cloneWithNewWeight(carrot, 15));
			lunchOk.addFood(cloneWithNewWeight(chicken, 300));
			
			Meal lunchBad = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 21));
			lunchBad.addFood(cloneWithNewWeight(chips, 100));
			lunchBad.addFood(cloneWithNewWeight(rice, 300));
			lunchBad.addFood(cloneWithNewWeight(carrot, 5));
			lunchBad.addFood(cloneWithNewWeight(chicken, 100));
			
			Meal dinnerOk = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 20));
			dinnerOk.addFood(cloneWithNewWeight(rice, 100));
			dinnerOk.addFood(cloneWithNewWeight(carrot, 10));
			dinnerOk.addFood(cloneWithNewWeight(chicken, 100));
			dinnerOk.addFood(cloneWithNewWeight(banana, 150));
			
			Meal dinnerBad = new Meal();
			breakfastOk.setDate(new Date(2017, 03, 21));
			dinnerBad.addFood(cloneWithNewWeight(rice, 100));
			dinnerBad.addFood(cloneWithNewWeight(chicken, 150));
			dinnerBad.addFood(cloneWithNewWeight(bread, 70));
			dinnerBad.addFood(cloneWithNewWeight(chips, 150));
			
			Meal snack1 = new Meal();
			snack1.addFood(cloneWithNewWeight(apple, 100));
			snack1.addFood(cloneWithNewWeight(banana, 100));
			
			Meal dinner = new Meal();
			dinner.addFood(cloneWithNewWeight(rice, 150));
			dinner.addFood(cloneWithNewWeight(chips, 150));
			
			//2days moderately active
			double totalCaloriesOut = 5000;
			//1 day active man
			totalCaloriesOut = 2000;
			
			myMealList.add(snack1);
			
			/*myMealList.add(breakfastOk);
			myMealList.add(lunchOk);
			myMealList.add(dinnerOk);
			*/
			
			myMealList.add(breakfastBad);
			myMealList.add(lunchBad);
			myMealList.add(dinnerBad);
			
			List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);
			
			double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
			
			System.out.println("Total calories in: " + totalCaloriesIn);
			
			AteMuchOrLess ateMuchOrLess = InsightsMethod.ateMuchOrLess(totalCaloriesIn, totalCaloriesOut);
			
			if("TOO_MUCH".equals(ateMuchOrLess.name())){
				//Insights.day(myMealList, totalCaloriesOut);
				InsightsMethod.week(myMealList, totalCaloriesOut);
			}
			
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