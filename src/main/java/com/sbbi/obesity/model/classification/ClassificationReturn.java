package com.sbbi.obesity.model.classification;

import java.util.List;

import com.sbbi.obesity.model.Food;

public class ClassificationReturn {

	private String food1Str;
	private Food food1;
	private List<FoodClassification> suggestionsFood1;
	private Food food2;
	private List<FoodClassification> suggestionsFood2;
	private Food food3;
	private List<FoodClassification> suggestionsFood3;
	
	public ClassificationReturn(){}

	public String getFood1Str() {
		return food1Str;
	}

	public void setFood1Str(String food1Str) {
		this.food1Str = food1Str;
	}

	public Food getFood1() {
		return food1;
	}

	public ClassificationReturn setFood1(Food food1) {
		this.food1 = food1;
		return this;
	}

	public List<FoodClassification> getSuggestionsFood1() {
		return suggestionsFood1;
	}

	public ClassificationReturn setSuggestionsFood1(List<FoodClassification> suggestionsFood1) {
		this.suggestionsFood1 = suggestionsFood1;
		return this;
	}

	public Food getFood2() {
		return food2;
	}

	public ClassificationReturn setFood2(Food food2) {
		this.food2 = food2;
		return this;
	}

	public List<FoodClassification> getSuggestionsFood2() {
		return suggestionsFood2;
	}

	public ClassificationReturn setSuggestionsFood2(List<FoodClassification> suggestionsFood2) {
		this.suggestionsFood2 = suggestionsFood2;
		return this;
	}

	public Food getFood3() {
		return food3;
	}

	public ClassificationReturn setFood3(Food food3) {
		this.food3 = food3;
		return this;
	}

	public List<FoodClassification> getSuggestionsFood3() {
		return suggestionsFood3;
	}

	public ClassificationReturn setSuggestionsFood3(List<FoodClassification> suggestionsFood3) {
		this.suggestionsFood3 = suggestionsFood3;
		return this;
	}
	
}
