package com.sbbi.obesity.insights;

import java.util.List;

import com.sbbi.obesity.constants.AteMuchOrLess;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.Meal;

public class Insights {

	//The Ideal Daily Caloric Deficit: 20% below maintenance level per day. 20% of 2500
	//http://www.acaloriecounter.com/diet/how-many-calories-should-i-eat-per-day-to-lose-weight/

	private static final double DEFICIT_FACTOR = 0.2;
	//for 2 days
	private static final double CALORIE_MAINTENANCE_LEVEL = 5600;
	//fod 1 day
	//private static final double AVERAGE_CALORIES = 2500;
	
	public static AteMuchOrLess ateMuchOrLess(double totalCaloriesIn, double totalCaloriesOut) {
		
		//boolean tooMuch = true;
		
		double energyBalance = totalCaloriesIn - totalCaloriesOut;
		System.out.println("Energy balance: " + energyBalance);
		
		if(tooMuch(energyBalance)){
			System.out.println("eating too much, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories");
			return AteMuchOrLess.TOO_MUCH;
		}
		else if(tooLess(energyBalance, totalCaloriesOut, totalCaloriesIn)){
			System.out.println("eating too little, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories");
			return AteMuchOrLess.TOO_LITTLE;
		}
		else{
			System.out.println("huuhuuuuul, good food habits, " + totalCaloriesIn + " calories in and " + totalCaloriesOut + " calories out");
			return AteMuchOrLess.NORMAL;
		}
			
		
	}

	private static boolean tooLess(double energyBalance, double totalCaloriesOut, double totalCaloriesIn) {
		
		double deficit = totalCaloriesOut - totalCaloriesOut * DEFICIT_FACTOR;
		
		//if(energyBalance < deficit)
		if(energyBalance <= 0 && totalCaloriesIn < deficit){
			return true;
		}
			
		return false;
	}

	private static boolean tooMuch(double energyBalance) {
		
		
		if(energyBalance > 0)
			return true;
		
		return false;
	}

	public static void day(List<FrequentItems> listFrequentItems) {
		double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
		
	}
	
}