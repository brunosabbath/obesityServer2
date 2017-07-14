package com.sbbi.obesity.manager;
import java.sql.Connection;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.sbbi.obesity.dao.MealDaoImpl;
import com.sbbi.obesity.dao.MealFoodDaoImpl;
import com.sbbi.obesity.helpers.DateHelper;
import com.sbbi.obesity.helpers.MealTypeEnum;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.SendMeal;
import com.sbbi.obesity.model.TypeMeal;
import com.sbbi.obesity.model.User;
import com.sbbi.obesity.model.pojo.MealPojo;

public class MealManager {

	private Connection connection;
	
	private HashMap<String, Food> hashFood;
	
	public MealManager(Connection connection) {
		this.connection = connection;
		hashFood = new HashMap<>();
		createFoodHash();
	}

	public MealManager() {}

	private void createFoodHash() {
		
		hashFood.put("Apple", new Food(1,"Apple"));
		hashFood.put("Banana", new Food(2,"Banana"));
		hashFood.put("Blueberry", new Food(3,"Blueberry"));
		hashFood.put("Carrot", new Food(4,"Carrot"));
		hashFood.put("Grilled chicken breast", new Food(5,"Grilled chicken breast"));
		hashFood.put("Chips", new Food(6,"Chips"));
		hashFood.put("Grape", new Food(7,"Grape"));
		hashFood.put("Orange", new Food(8,"Orange"));
		hashFood.put("Peach", new Food(9,"Peach"));
		hashFood.put("Pear", new Food(10,"Pear"));
		hashFood.put("Raspberry", new Food(11,"Raspberry"));
		hashFood.put("Rice", new Food(12,"Rice"));
		hashFood.put("Sandwich bread", new Food(13,"Sandwich bread"));
	}
	
	public List<Meal> listTodaysMeals(int userId) throws ParseException{
		
		MealDaoImpl dao = new MealDaoImpl(connection);
		return dao.listTodaysMeals(userId, DateHelper.getTodayTimestamp(), DateHelper.getTomorrowTimestamp());
		
	}
	
	public void createMeal(Meal meal, double quantity[]){
		
		MealDaoImpl mealDao = new MealDaoImpl(connection);
		int mealId = mealDao.insert(meal);
		//mealDao.close();
		
		for(int i = 0; i < meal.getFoods().size(); i++){
			meal.getFoods().get(i).changeAmountGrams(quantity[i]);
		}
		
		/*MealFood mealFood = new MealFood();
		MealFoodDaoImpl mealFoodDao = new MealFoodDaoImpl(connection);
		mealFoodDao.insert(meal, mealId, quantity);*/
		
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void post(SendMeal sendMeal) {
		
		int mealId = saveMeal(sendMeal);
		MealFoodDaoImpl dao = new MealFoodDaoImpl(connection);
		
		if(sendMeal.getFood1() != null){
			int food1Id = getFoodId(sendMeal.getFood1());
			dao.insert(mealId, food1Id, sendMeal.getWeightFood1());
		}
		
		if(sendMeal.getFood2() != null){
			int food2Id = getFoodId(sendMeal.getFood2());
			dao.insert(mealId, food2Id, sendMeal.getWeightFood2());
		}

		if(sendMeal.getFood3() != null){
			int food3Id = getFoodId(sendMeal.getFood3());
			dao.insert(mealId, food3Id, sendMeal.getWeightFood3());
		}
		
	}

	private int getFoodId(String foodName) {
		
		Food food = hashFood.get(foodName);
		
		return food.getId();
	}

	private int saveMeal(SendMeal sendMeal) {
		
		Meal meal = new Meal();
		TypeMeal typeMeal = new TypeMeal(sendMeal.getTypeMeal());
		
		meal.setDate(new Date()).setTypeMeal(typeMeal);
		
		User user = new User();
		user.setId(sendMeal.getUserId());
		meal.setUser(user);
		
		MealDaoImpl mealDao = new MealDaoImpl(connection);
		int mealId = mealDao.insert(meal);
		
		return mealId;
	}

	public List<MealPojo> list(Integer userId) {
		
		MealDaoImpl dao = new MealDaoImpl(connection);
		List<MealPojo> mealList = dao.list(userId);
		return mealList;
		
	}

	public String insightSkippedMeal(int userId) {
		
		int todaysMonth = DateHelper.getCurrentMonth(); 
		int todaysDay = DateHelper.getTodaysDay();
		int year = DateHelper.getYear();
		
		int hours = DateHelper.getHour();
		
		Date startDay = DateHelper.getStartDay();
		Date endDay = DateHelper.getEndDay();
		
		//new MealDaoImpl(connection).listTodaysMeals(userId, DateHelper.dateToSql(startDay), DateHelper.dateToSql(endDay));
		MealDaoImpl dao = new MealDaoImpl(connection);
		
		if(hours >= 10 && hours <= 15){
			boolean ateMeal = dao.ateMeal(userId, MealTypeEnum.LUNCH.getValue(), DateHelper.getLunchStart(), DateHelper.getLunchEnd());
			
			if(!ateMeal){
				System.out.println("You haven't eaten lunch, don't eat too much during dinner");
			}
			
		}
		else if(hours >= 17 && hours <= 22){
			boolean ateMeal = dao.ateMeal(userId, MealTypeEnum.DINNER.getValue(), DateHelper.getDinnerStart(), DateHelper.getDinnerEnd());
			
			if(!ateMeal){
				System.out.println("You haven't eaten dinner");
			}
			
		}
		else if(hours <= 10){
			boolean ateMeal = dao.ateMeal(userId, MealTypeEnum.BREAKFAST.getValue(), DateHelper.getBreakfastStart(), DateHelper.getBreakfastEnd());
			
			if(!ateMeal){
				System.out.println("You haven't eaten breakfast, don't eat too much during lunch");
			}
			
		}
		
		close();
		return null;
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addConenction(Connection connection) {
		this.connection = connection;
		
	}

	public void createMeal(Food foodLeft, Food foodRight, Food foodBottom, Integer userId, long timestamp, int typeMeal) {
		
		MealDaoImpl dao = new MealDaoImpl(connection);
		Meal meal = new Meal();
		meal.setTypeMeal(new TypeMeal(typeMeal));
		meal.setUser(new User(userId));
		meal.setDate(new Date(timestamp));
		
		int mealId = dao.insert(meal);
		
		MealFoodDaoImpl mealFoodDaoImpl = new MealFoodDaoImpl(connection);
		mealFoodDaoImpl.insert(mealId, foodLeft.getId(), foodLeft.getGrams());
		mealFoodDaoImpl.insert(mealId, foodRight.getId(), foodRight.getGrams());
		mealFoodDaoImpl.insert(mealId, foodBottom.getId(), foodBottom.getGrams());
		
		close();
	}
	
}
