package com.sbbi.obesity.insights;

public class Insights {

	//The Ideal Daily Caloric Deficit: 20% below maintenance level per day. 20% of 2500
	//http://www.acaloriecounter.com/diet/how-many-calories-should-i-eat-per-day-to-lose-weight/

	private static final double DEFICIT_FACTOR = 0.2;
	private static final double AVERAGE_CALORIES = 2500;
	
	public static void ateMuchOrLess(double totalCaloriesIn, double totalCaloriesOut) {
		
		//boolean tooMuch = true;
		
		if(tooMuch(totalCaloriesIn, totalCaloriesOut)){
			System.out.println("eating too much");
		}
		else if(tooLess(totalCaloriesIn, totalCaloriesOut)){
			System.out.println("eating too little");
		}
		else{
			System.out.println("eating normal");
		}
			
		
	}

	private static boolean tooLess(double totalCaloriesIn, double totalCaloriesOut) {
		
		double totalCaloriesOutWithDeficit = totalCaloriesIn - (totalCaloriesIn * DEFICIT_FACTOR);
		
		if(totalCaloriesOutWithDeficit <= AVERAGE_CALORIES)
			return true;
		
		return false;
	}

	private static boolean tooMuch(double totalCaloriesIn, double totalCaloriesOut) {
		
		if(totalCaloriesIn > totalCaloriesOut)
			return true;
		
		return false;
	}
	
}