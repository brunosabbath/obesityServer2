package com.sbbi.obesity.model;

public class FoodWithWeight {

	private double weight;
	private double volume;
	private String name;

	public FoodWithWeight(){}
	
	public FoodWithWeight(double weight, double volume, String name) {
		this.weight = weight;
		this.volume = volume;
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
