package com.sbbi.obesity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.TypeMeal;

public class Builder {

	public static Food buildFood(ResultSet rs){
		Food food = new Food();
		try {
			food.setId(rs.getInt(1)).setName(rs.getString(2)).setEnergy(rs.getDouble(3)).setProtein(rs.getDouble(4))
			.setLipid(rs.getDouble(5)).setCarbohydrate(rs.getDouble(6)).setFiber(rs.getDouble(7)).setSugar(rs.getDouble(8))
			.setFattyAcidsSaturated(rs.getDouble(9)).setFattyAcidsMonounsaturated(rs.getDouble(10)).setFattyAcidsPolyunsaturated(rs.getDouble(11))
			.setFattyAcidTrans(rs.getDouble(12)).setCholesterol(rs.getDouble(13));
			
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
	
}
