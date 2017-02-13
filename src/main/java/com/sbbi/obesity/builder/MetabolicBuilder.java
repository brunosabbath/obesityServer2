package com.sbbi.obesity.builder;

import com.sbbi.obesity.manager.MetabolicManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.response.ResponseFood;

public class MetabolicBuilder {
	
	private static final int TOTAL_ARRAY = 4;
	private static double foodArray[];
	
	public static MetabolicManager build(ResponseFood response) {
		
		MetabolicManager manager = new MetabolicManager();
		
		if(response.getNutrientsFood1() != null){
			double[] array1 = transformToPojo(response.getNutrientsFood1());
			manager.setArrayFood1(array1);
		}
		
		if(response.getNutrientsFood2() != null){
			double[] array2 = transformToPojo(response.getNutrientsFood1());
			manager.setArrayFood2(array2);
		}
		
		if(response.getNutrientsFood3() != null){
			double[] array3 = transformToPojo(response.getNutrientsFood1());
			manager.setArrayFood3(array3);
		}
		
		return manager;
	}
	
	public static double[] transformToPojo(Food food){
		foodArray = new double[TOTAL_ARRAY];
		foodArray[0] = food.getProtein();
		foodArray[1] = food.getLipid();
		foodArray[2] = food.getCarbohydrate();
		foodArray[3] = food.getSugar();
		return foodArray;
	}
	
}
