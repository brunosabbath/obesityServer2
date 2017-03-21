package com.sbbi.obesity.model;

import java.util.ArrayList;
import java.util.List;

public class Insight {

	private double totalCaloriesIn;
	private double totalCaloriesOut;
	private double totalCarbs;
	private double totalProtein;
	private double totalSugar;
	private String highestProteinName;
	private String highestCarbsName;
	private String highestSugarName;
	private String highestCaloriesName;
	private List<FrequentItems> unhealthyFood;
	
	public void setUnhealthyFood(List<FrequentItems> unhealthyFood) {
		this.unhealthyFood = unhealthyFood;
	}

	public Insight(){
		unhealthyFood = new ArrayList<FrequentItems>();
	}

	public double getTotalCaloriesIn() {
		return totalCaloriesIn;
	}

	public Insight setTotalCaloriesIn(double totalCaloriesIn) {
		this.totalCaloriesIn = totalCaloriesIn;
		return this;
	}

	public double getTotalCaloriesOut() {
		return totalCaloriesOut;
	}

	public Insight setTotalCaloriesOut(double totalCaloriesOut) {
		this.totalCaloriesOut = totalCaloriesOut;
		return this;
	}

	public double getTotalCarbs() {
		return totalCarbs;
	}

	public Insight setTotalCarbs(double totalCarbs) {
		this.totalCarbs = totalCarbs;
		return this;
	}

	public double getTotalProtein() {
		return totalProtein;
	}

	public Insight setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
		return this;
	}

	public double getTotalSugar() {
		return totalSugar;
	}

	public Insight setTotalSugar(double totalSugar) {
		this.totalSugar = totalSugar;
		return this;
	}

	public String getHighestProteinName() {
		return highestProteinName;
	}

	public Insight setHighestProteinName(String highestProteinName) {
		this.highestProteinName = highestProteinName;
		return this;
	}

	public String getHighestCarbsName() {
		return highestCarbsName;
	}

	public Insight setHighestCarbsName(String highestCarbsName) {
		this.highestCarbsName = highestCarbsName;
		return this;
	}

	public String getHighestSugarName() {
		return highestSugarName;
	}

	public Insight setHighestSugarName(String highestSugarName) {
		this.highestSugarName = highestSugarName;
		return this;
	}

	public String getHighestCaloriesName() {
		return highestCaloriesName;
	}

	public Insight setHighestCaloriesName(String highestCaloriesName) {
		this.highestCaloriesName = highestCaloriesName;
		return this;
	}

	public List<FrequentItems> getUnhealthyFood() {
		return unhealthyFood;
	}

	public void addUnhealthyFood(FrequentItems f) {
		this.unhealthyFood.add(f);
	}
	
}
