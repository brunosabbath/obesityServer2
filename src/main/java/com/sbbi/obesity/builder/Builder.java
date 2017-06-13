package com.sbbi.obesity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.TypeMeal;
import com.sbbi.obesity.model.User;

public class Builder {

	public static Food buildFood(ResultSet rs){
		Food food = new Food();
		try {
			food.setId(rs.getInt(1)).setName(rs.getString(2)).setEnergy(rs.getDouble(3)).setProtein(rs.getDouble(4))
			.setLipid(rs.getDouble(5)).setCarbohydrate(rs.getDouble(6)).setFiber(rs.getDouble(7)).setSugar(rs.getDouble(8))
			.setFattyAcidsSaturated(rs.getDouble(9)).setFattyAcidsMonounsaturated(rs.getDouble(10)).setFattyAcidsPolyunsaturated(rs.getDouble(11))
			.setCholesterol(rs.getDouble(12)).setCalcium(rs.getDouble(13)).setIron(rs.getDouble(14)).setPotassium(rs.getDouble(15)).setSodium(rs.getDouble(16)).setGrade(rs.getString(17).charAt(0));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return food;
	}

	public static TypeMeal buildTypeMeal(ResultSet rs) {
		
		TypeMeal type = new TypeMeal();
		
		try {
			type.setId(rs.getInt(1));
			type.setType(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	}

	public static User buildUser(ResultSet rs) {
		
		User user = new User();
		
		try {
			user.setId(rs.getInt(1)).setName(rs.getString(2)).setHeight(rs.getDouble(5)).setWeight(rs.getDouble(6)).setSex(rs.getString(7).charAt(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public static Food buildFood(double quantityInGrams, String foodName, double energy, double protein, double lipid,
			double carbohydrate, double fiber, double sugar, double fattyAcidsSaturated,
			double fattyAcidsMonounsaturated, double fattyAcidsPolyunsaturated, double fattyAcidTrans,
			double cholesterol) {
		
		Food food = new Food();
		food.setName(foodName).setEnergy(energy).setProtein(protein).setLipid(lipid).setCarbohydrate(carbohydrate).setFiber(fiber).setSugar(sugar).setFattyAcidsSaturated(fattyAcidsSaturated)
		.setFattyAcidsMonounsaturated(fattyAcidsMonounsaturated).setFattyAcidsPolyunsaturated(fattyAcidsPolyunsaturated).setFattyAcidTrans(fattyAcidTrans);
		
		food.changeAmountGrams(quantityInGrams);
		
		return food;
	}
	
}
