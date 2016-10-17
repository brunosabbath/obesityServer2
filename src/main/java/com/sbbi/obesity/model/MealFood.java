package com.sbbi.obesity.model;

import java.io.Serializable;
import javax.persistence.*;


public class MealFood implements Serializable {
	private static final long serialVersionUID = 1L;

	private MealFoodPK id;

	private double quantity;

	//bi-directional many-to-one association to Meal
	private Meal meal;

	//bi-directional many-to-one association to Food
	private Food food;

	public MealFood() {
	}

	public MealFood(Food food) {
		this.food = food;
	}

	public MealFoodPK getId() {
		return this.id;
	}

	public void setId(MealFoodPK id) {
		this.id = id;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Meal getMeal() {
		return this.meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Food getFood() {
		return this.food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

}