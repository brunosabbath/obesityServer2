package com.sbbi.obesity.model.pojo;

import java.util.ArrayList;
import java.util.List;

public class MealPojo {

	private String type;
	
	private List<FoodPojo> listFood;
	
	public MealPojo(){
		listFood = new ArrayList<FoodPojo>();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FoodPojo> getListFood() {
		return listFood;
	}

	public void addFood(FoodPojo food) {
		this.listFood.add(food);
	}
	
}
