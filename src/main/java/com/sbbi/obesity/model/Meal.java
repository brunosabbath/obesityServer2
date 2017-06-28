package com.sbbi.obesity.model;


import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Meal implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Date date;
	private List<Food> foods;
	private List<Photo> photos;
	private User user;
	private TypeMeal typeMeal;
	private boolean eatingOutside;
	
	public Meal() {
		foods = new ArrayList<Food>();
		photos = new ArrayList<Photo>();
		eatingOutside = false;
	}

	public boolean isEatingOutside(){
		return eatingOutside;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public Meal setDate(Date date) {
		this.date = date;
		return this;
	}


	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setMeal(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setMeal(null);

		return photo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addFood(Food food){
		this.foods.add(food);
	}
	
	public List<Food> getFoods(){
		return this.foods;
	}
	
	public TypeMeal getTypeMeal() {
		return this.typeMeal;
	}

	public void setTypeMeal(TypeMeal typeMealBean) {
		this.typeMeal = typeMealBean;
	}
	
	public double getTotalCaloriesMeal(){
		double totalCalories = 0;
		
		for(Food food : foods)
			totalCalories = totalCalories + food.getEnergy();
		
		return totalCalories;
	}
	
}