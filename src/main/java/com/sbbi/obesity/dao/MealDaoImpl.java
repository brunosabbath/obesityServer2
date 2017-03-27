package com.sbbi.obesity.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.helpers.DateHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.TypeMeal;
import com.sbbi.obesity.model.pojo.FoodPojo;
import com.sbbi.obesity.model.pojo.MealPojo;

public class MealDaoImpl {

	private static final int NOT_FOUND = -1;
	private Connection connection;
	
	public MealDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	public int insert(Meal meal){
		
		int id = NOT_FOUND;
		
		try {
			
			int eatingOutside = 0;
			
			if(meal.isEatingOutside()){
				eatingOutside = 1;
			}
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO meal (user_id, type_meal, date, eatingOutside) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, meal.getUser().getId());
			ps.setInt(2, meal.getTypeMeal().getId());
			ps.setTimestamp(3, DateHelper.dateToSql(meal.getDate()));
			ps.setInt(4, eatingOutside);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				id = rs.getInt(1);
				
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//public List<Meal> getMeal(int userId){
	public List<Meal> getMeal(){
		
		PreparedStatement ps = null;
		
		Food food = new Food();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM food WHERE name = ?");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				food = Builder.buildFood(rs);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Meal> listType(TypeMeal type, int userId){
		
		PreparedStatement ps = null;
		List<Meal> listMeal = new ArrayList<Meal>();
		
		try {
			ps = connection.prepareStatement("SELECT m.id, m.date, m.eatingOutside, m.type_meal, mf.quantity, f.name, f.energy, f.protein, f.lipid, f.carbohydrate, f.fiber, f.sugar, f.fatty_acids_saturated, f.fatty_acids_monounsaturated, f.fatty_acids_polyunsaturated, f.fatty_acid_trans, f.cholesterol  FROM meal AS m " + 
												"INNER JOIN mealFood AS mf ON mf.mealId = m.id " + 
												"INNER JOIN food AS f ON f.id = mf.foodId " +
												"WHERE m.user_id = ? AND m.type_meal = ?;");
			ps.setInt(1, type.getId());
			ps.setInt(2, userId);
			
			ResultSet rs = ps.executeQuery();
			
			int previousMealId = 0;
			Meal meal = new Meal();
			
			
			while(rs.next()){
				int mealId = rs.getInt(1);
				int eatingOutside = rs.getInt(3);
				
				double quantity = rs.getDouble(5);
				
				String foodName = rs.getString(6);
				double energy = rs.getDouble(7);
				double protein = rs.getDouble(8);
				double lipid = rs.getDouble(9);
				double carbohydrate = rs.getDouble(10);
				double fiber = rs.getDouble(11);
				double sugar = rs.getDouble(12);
				double fatty_acids_saturated = rs.getDouble(13);
				double fatty_acids_monounsaturated = rs.getDouble(14);
				double fatty_acids_polyunsaturated = rs.getDouble(15);
				double fatty_acid_trans = rs.getDouble(16);
				double cholesterol = rs.getDouble(17);
				
				if(mealId == previousMealId){
					meal.addFood(Builder.buildFood(quantity, foodName, energy, protein, lipid, carbohydrate, fiber, sugar, fatty_acids_saturated, fatty_acids_monounsaturated, fatty_acids_polyunsaturated, fatty_acid_trans, cholesterol));
				}
				else{
					listMeal.add(meal);
					meal = new Meal();
					meal.addFood(Builder.buildFood(quantity, foodName, energy, protein, lipid, carbohydrate, fiber, sugar, fatty_acids_saturated, fatty_acids_monounsaturated, fatty_acids_polyunsaturated, fatty_acid_trans, cholesterol));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listMeal;
		
	}
	
	public List<MealPojo> list(Integer userId) {

		PreparedStatement ps = null;
		
		List<MealPojo> list = new ArrayList<MealPojo>();
		
		try {
			ps = connection.prepareStatement("SELECT m.id, m.date, tm.type, m.eatingOutside FROM meal AS m INNER JOIN typeMeal AS tm ON tm.id = m.type_meal WHERE m.user_id = ?");
			ps.setInt(1,userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				boolean eatingOutside = false;
				
				if(rs.getInt(4) == 1)
					eatingOutside = true;
				
				String type = rs.getString(3);
				int mealId = rs.getInt(1);
				
				MealPojo mealPojo = new MealPojo();
				mealPojo.setType(type);
				mealPojo.setEatingOutside(eatingOutside);
				
				PreparedStatement psFood = connection.prepareStatement("SELECT mf.quantity, f.name, f.energy, f.protein, f.lipid, f.carbohydrate, f.fiber, f.sugar, f.cholesterol " + 
				"FROM mealFood as mf INNER JOIN food AS f on f.id = mf.foodId WHERE mealId = ?");
				
				psFood.setInt(1, mealId);
				
				ResultSet rsFood = psFood.executeQuery();
				
				while(rsFood.next()){
					FoodPojo pojo = new FoodPojo();
					
					double quantity = rsFood.getDouble(1);
					
					pojo.setName(rsFood.getString(2)).setEnergy(rsFood.getDouble(3)).setProtein(rsFood.getDouble(4))
					.setLipid(rsFood.getDouble(5)).setCarbohydrate(rsFood.getDouble(6)).setFiber(rsFood.getDouble(7))
					.setSugar(rsFood.getDouble(8)).setCholesterol(rsFood.getDouble(9));
					
					pojo.changeAmountGrams(quantity);
					
					mealPojo.addFood(pojo);
				}
				
				list.add(mealPojo);
				
			}
			
			return list;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Meal> listTodaysMeals(int userId, Timestamp startDay, Timestamp endDay) {
		
		PreparedStatement ps = null;
		List<Meal> listMeal = new ArrayList<Meal>();
		
		try {
			ps = connection.prepareStatement("SELECT m.id, m.date, m.eatingOutside, m.type_meal, mf.quantity, f.name, f.energy, f.protein, f.lipid, f.carbohydrate, f.fiber, f.sugar, f.fatty_acids_saturated, f.fatty_acids_monounsaturated, f.fatty_acids_polyunsaturated, f.fatty_acid_trans, f.cholesterol  FROM meal AS m " + 
												"INNER JOIN mealFood AS mf ON mf.mealId = m.id " + 
												"INNER JOIN food AS f ON f.id = mf.foodId " +
												"WHERE m.user_id = ? AND m.date >= ? AND m.date <= ?;");
			ps.setInt(1, userId);
			ps.setTimestamp(2, startDay);
			ps.setTimestamp(3, endDay);
			
			ResultSet rs = ps.executeQuery();
			
			int previousMealId = 0;
			Meal meal = new Meal();
			
			
			while(rs.next()){
				int mealId = rs.getInt(1);
				int eatingOutside = rs.getInt(3);
				
				double quantity = rs.getDouble(5);
				
				String foodName = rs.getString(6);
				double energy = rs.getDouble(7);
				double protein = rs.getDouble(8);
				double lipid = rs.getDouble(9);
				double carbohydrate = rs.getDouble(10);
				double fiber = rs.getDouble(11);
				double sugar = rs.getDouble(12);
				double fatty_acids_saturated = rs.getDouble(13);
				double fatty_acids_monounsaturated = rs.getDouble(14);
				double fatty_acids_polyunsaturated = rs.getDouble(15);
				double fatty_acid_trans = rs.getDouble(16);
				double cholesterol = rs.getDouble(17);
				
				if(mealId == previousMealId){
					meal.addFood(Builder.buildFood(quantity, foodName, energy, protein, lipid, carbohydrate, fiber, sugar, fatty_acids_saturated, fatty_acids_monounsaturated, fatty_acids_polyunsaturated, fatty_acid_trans, cholesterol));
				}
				else{
					listMeal.add(meal);
					meal = new Meal();
					meal.addFood(Builder.buildFood(quantity, foodName, energy, protein, lipid, carbohydrate, fiber, sugar, fatty_acids_saturated, fatty_acids_monounsaturated, fatty_acids_polyunsaturated, fatty_acid_trans, cholesterol));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listMeal;
	}
	
	
}