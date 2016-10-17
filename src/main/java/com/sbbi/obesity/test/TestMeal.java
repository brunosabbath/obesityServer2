package com.sbbi.obesity.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.manager.MealManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.MealFood;
import com.sbbi.obesity.model.TypeMeal;

public class TestMeal {

	public static void main(String[] args) {
		
		try {
			
			FoodDaoImpl foodDao = new FoodDaoImpl(ConnectionFactory.getConnection());
			MealManager manager = new MealManager(ConnectionFactory.getConnection());
			
			Food food1 = foodDao.getByName("Apple");
			Food food2 = foodDao.getByName("Blueberry");
			
			List<Food> list = new ArrayList<Food>();
			list.add(food1);
			list.add(food2);
			
			int typeMeal = 1;
			
			double quantity[] = new double[2];
			quantity[0] = 150;
			quantity[1] = 150;
			
			Meal meal = new Meal();
			List<MealFood> mealFoodList = new ArrayList<MealFood>();
			
			for(Food food : list){
				mealFoodList.add(new MealFood(food));
			}
			
			meal.setMealFoods(mealFoodList);
			meal.setTypeMeal(new TypeMeal(typeMeal));
			meal.setDate(new Date());
			
			manager.createMeal(meal, quantity);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
