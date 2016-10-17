package com.sbbi.obesity.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mealFood database table.
 * 
 */
public class MealFoodPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int mealId;

	private int foodId;

	public MealFoodPK() {
	}
	public int getMealId() {
		return this.mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	public int getFoodId() {
		return this.foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MealFoodPK)) {
			return false;
		}
		MealFoodPK castOther = (MealFoodPK)other;
		return 
			(this.mealId == castOther.mealId)
			&& (this.foodId == castOther.foodId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mealId;
		hash = hash * prime + this.foodId;
		
		return hash;
	}
}