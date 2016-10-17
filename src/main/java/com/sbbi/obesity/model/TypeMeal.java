package com.sbbi.obesity.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the typeMeal database table.
 * 
 */
public class TypeMeal implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String type;

	//bi-directional many-to-one association to Meal
	private List<Meal> meals;

	public TypeMeal() {
	}

	public TypeMeal(int typeMeal) {
		this.id = typeMeal;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Meal> getMeals() {
		return this.meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	@Override
	public String toString() {
		return "id: " + id + " name: " + type;
	}
	
	

}