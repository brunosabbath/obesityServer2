package com.sbbi.obesity.response;

import com.sbbi.obesity.model.Food;

public class ResponseFood {

	private String food1[];
	private String food2[];
	private String food3[];
	private double weightFood1;
	private double weightFood2;
	private double weightFood3;
	private Food nutrientsFood1;
	private Food nutrientsFood2;
	private Food nutrientsFood3;
	
	public ResponseFood(){}
	
	public Food getNutrientsFood1() {
		return nutrientsFood1;
	}

	public ResponseFood setNutrientsFood1(Food nutrientsFood1) {
		this.nutrientsFood1 = nutrientsFood1;
		return this;
	}

	public Food getNutrientsFood2() {
		return nutrientsFood2;
	}

	public ResponseFood setNutrientsFood2(Food nutrientsFood2) {
		this.nutrientsFood2 = nutrientsFood2;
		return this;
	}

	public Food getNutrientsFood3() {
		return nutrientsFood3;
	}

	public ResponseFood setNutrientsFood3(Food nutrientsFood3) {
		this.nutrientsFood3 = nutrientsFood3;
		return this;
	}



	public double getWeightFood1() {
		return weightFood1;
	}

	public ResponseFood setWeightFood1(double weightFood1) {
		this.weightFood1 = weightFood1;
		return this;
	}

	public double getWeightFood2() {
		return weightFood2;
	}

	public ResponseFood setWeightFood2(double weightFood2) {
		this.weightFood2 = weightFood2;
		return this;
	}

	public double getWeightFood3() {
		return weightFood3;
	}

	public ResponseFood setWeightFood3(double weightFood3) {
		this.weightFood3 = weightFood3;
		return this;
	}

	public String[] getFood1() {
		return food1;
	}

	public ResponseFood setFood1(String[] food1) {
		this.food1 = food1;
		return this;
	}

	public String[] getFood2() {
		return food2;
	}

	public ResponseFood setFood2(String[] food2) {
		this.food2 = food2;
		return this;
	}

	public String[] getFood3() {
		return food3;
	}

	public ResponseFood setFood3(String[] food3) {
		this.food3 = food3;
		return this;
	}
	
}
