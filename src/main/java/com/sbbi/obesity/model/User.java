package com.sbbi.obesity.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String email;

	private String height;

	private String name;

	private String password;

	private double weight;

	//bi-directional many-to-one association to Meal
	private List<Meal> meals;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getHeight() {
		return this.height;
	}

	public User setHeight(String height) {
		this.height = height;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public double getWeight() {
		return this.weight;
	}

	public User setWeight(double weight) {
		this.weight = weight;
		return this;
	}

	public List<Meal> getMeals() {
		return this.meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Meal addMeal(Meal meal) {
		getMeals().add(meal);
		meal.setUser(this);

		return meal;
	}

}