package com.sbbi.obesity.model;

public class FrequentItems implements Comparable<FrequentItems>{

	private int frequency;
	private String name;
	private double calories;
	
	public FrequentItems(String name, int frequency, double calories) {
		this.name = name;
		this.frequency = frequency;
		this.calories = calories;
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
		if(o.getFrequency() > this.getFrequency())
			return 1;
		else
			return -1;
	} 
	
	@Override
	public String toString() {
		return "Food: " + name + "\tCalories: " + calories + "\tFrequency: " + frequency;
	}
	
}