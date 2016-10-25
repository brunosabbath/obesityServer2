package com.sbbi.obesity.model.classification;

public class FoodClassification implements Comparable<FoodClassification>{

	private int id;
	private String foodName;
	private double value;
	
	public FoodClassification(int id, String foodName, double value){
		this.id = id;
		this.foodName = foodName;
		this.value = value;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public FoodClassification setFoodName(String foodName) {
		this.foodName = foodName;
		return this;
	}
	public double getValue() {
		return value;
	}
	public FoodClassification setValue(double value) {
		this.value = value;
		return this;
	}
	
	@Override
	public String toString() {
		return foodName + " -> " + value;
	}
	
	@Override
	public int compareTo(FoodClassification other) {
		if(other.getValue() >= this.getValue())
			return 1;
		else
			return -1;
	}
	
}