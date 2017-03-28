package com.sbbi.obesity.helpers;

public enum MealTypeEnum {
	BREAKFAST(1), LUNCH(2), DINNER(3), SNACK(4);
	private int value;
	
	private MealTypeEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
