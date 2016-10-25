package com.sbbi.obesity.model;

public class FoodMatlab implements Comparable<FoodMatlab>{

	private String foodName;
	private double value;
	
	public FoodMatlab(String foodName, double value){
		this.foodName = foodName;
		this.value = value;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public FoodMatlab setFoodName(String foodName) {
		this.foodName = foodName;
		return this;
	}
	public double getValue() {
		return value;
	}
	public FoodMatlab setValue(double value) {
		this.value = value;
		return this;
	}
	
	@Override
	public String toString() {
		return foodName + " -> " + value;
	}
	
	@Override
	public int compareTo(FoodMatlab other) {
		if(other.getValue() >= this.getValue())
			return 1;
		else
			return -1;
	}
	
}