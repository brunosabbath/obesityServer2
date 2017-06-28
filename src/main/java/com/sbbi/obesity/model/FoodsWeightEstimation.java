package com.sbbi.obesity.model;

public class FoodsWeightEstimation {

	private Food food1;
	private Food food2;
	private Food food3;
	private int typeFood;

	public FoodsWeightEstimation(Food food1, Food food2, Food food3, int typeFood){
		this.food1 = food1;
		this.food2 = food2;
		this.food3 = food3;
		this.typeFood = typeFood;
	}
	
	public Food getFood1() {
		return food1;
	}
	public void setFood1(Food food1) {
		this.food1 = food1;
	}
	public Food getFood2() {
		return food2;
	}
	public void setFood2(Food food2) {
		this.food2 = food2;
	}
	public Food getFood3() {
		return food3;
	}
	public void setFood3(Food food3) {
		this.food3 = food3;
	}
	public int getTypeFood() {
		return typeFood;
	}
	public void setTypeFood(int typeFood) {
		this.typeFood = typeFood;
	}
	
}