package com.sbbi.obesity.model;

public class FrequentItems implements Comparable<FrequentItems>{

	private int frequency;
	private String name;
	private double calories;
	private double carbs;
	private double sugars;
	private double protein;
	private double grams;
	private double energy;
	private char grade;
	
	public FrequentItems(String name, int frequency, double calories, double carbs, double protein, double sugar, double grams, char grade) {
		this.name = name;
		this.frequency = frequency;
		this.calories = calories;
		this.carbs = carbs;
		this.sugars = sugar;
		this.protein = protein;
		this.grams = grams;
		this.grade = grade;
	}

	public FrequentItems() {
		this.carbs = 0;
		this.sugars = 0;
		this.protein = 0;
		this.grams = 0;
	}
	
	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public double getGrams() {
		return grams;
	}

	public void setGrams(double grams) {
		this.grams = grams;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getSugars() {
		return sugars;
	}

	public void setSugars(double sugars) {
		this.sugars = sugars;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(FrequentItems o) {
		/*if(o.getFrequency() > this.getFrequency())
			return 1;
		else
			return -1;*/
		if(o.getCalories() > this.getCalories())
			return 1;
		else
			return -1;
	} 
	
	@Override
	public String toString() {
		return "Food: " + name + "\tCalories: " + calories + "\tcarbs: " + carbs + "\tsugars: " + sugars + "\tprotein: " + protein + "\tFrequency: " + frequency;
	}
	
}