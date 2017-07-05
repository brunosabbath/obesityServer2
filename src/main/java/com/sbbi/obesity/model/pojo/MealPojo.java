package com.sbbi.obesity.model.pojo;

import java.util.ArrayList;
import java.util.List;



public class MealPojo {

	private String type;
	private boolean eatingOutside;
	private List<FoodPojo> listFood;
	private String date;
	
	public MealPojo(){
		listFood = new ArrayList<FoodPojo>();
	}
	
	public void setEatingOutside(boolean eatingOutside){
		this.eatingOutside = eatingOutside;
	}
	
	public boolean isEatingOutside(){
		return eatingOutside;
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

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
}