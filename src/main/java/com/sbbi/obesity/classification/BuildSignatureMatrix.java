package com.sbbi.obesity.classification;

import java.util.List;

import com.sbbi.obesity.model.Meal;

public class BuildSignatureMatrix {
	
	private static int TOTAL_FOOD_DB = 13;
	private static String[] foods = {"Apple", "Banana", "Blueberry", "Carrot", "Grilled chicken breast", "Chips", "Grape", "Orange", "Peach", "Pear", "Raspberry", "Rice", "Sandwich bread"};
	
	public void build(List<Meal> listMeal){
		
		int signature[][] = new int[TOTAL_FOOD_DB][listMeal.size()];
		
		
		
		//return ;
	}
	
	public int[][] buildSignatureMatrix(List<Meal> listMeal){
		
		int signature[][] = new int[1][1];
		
		return signature;
	}
	
}
