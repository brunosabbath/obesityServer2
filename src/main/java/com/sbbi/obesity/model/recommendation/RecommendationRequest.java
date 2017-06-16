package com.sbbi.obesity.model.recommendation;

import java.util.List;

import com.sbbi.obesity.model.Meal;

public class RecommendationRequest {

	private int userId;
	private int caloriesOut;
	private List<Meal> listMeal;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCaloriesOut() {
		return caloriesOut;
	}
	public void setCaloriesOut(int caloriesOut) {
		this.caloriesOut = caloriesOut;
	}
	public List<Meal> getListMeal() {
		return listMeal;
	}
	public void setListMeal(List<Meal> listMeal) {
		this.listMeal = listMeal;
	}
	
}