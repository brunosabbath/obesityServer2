package com.sbbi.obesity.insights;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbbi.obesity.constants.AteMuchOrLess;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.FrequentItemsComparator;
import com.sbbi.obesity.model.Insight;
import com.sbbi.obesity.model.Meal;

public class InsightsMethod {

	//The Ideal Daily Caloric Deficit: 20% below maintenance level per day. 20% of 2500
	//http://www.acaloriecounter.com/diet/how-many-calories-should-i-eat-per-day-to-lose-weight/

	private static final double THRESHOLD_BAD_FOOD = 35;
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

	public static void day(List<Meal> myMealList, double totalCaloriesOut) {
		List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);
		double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
		
		Insight insight = getInsights(listFrequentItems, totalCaloriesIn, totalCaloriesOut);
		
	}
	
	public static void week(List<Meal> myMealList, double totalCaloriesOut) {
		List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);
		double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);
		
		Insight insight = getInsights(listFrequentItems, totalCaloriesIn, totalCaloriesOut);
		
		new Recommendation().getRecommendation(insight, listFrequentItems, myMealList, totalCaloriesIn, totalCaloriesOut);
	}
	
	private static Insight getInsights(List<FrequentItems> listFrequentItems, double totalCaloriesIn, double totalCaloriesOut) {
		
		double totalCarbs = 0;
		double totalProtein = 0;
		double totalSugar = 0;
		FrequentItems highestCarbs = new FrequentItems();
		FrequentItems highestProtein = new FrequentItems();
		FrequentItems highestSugars = new FrequentItems();
		
		for(FrequentItems f : listFrequentItems){
			totalCarbs = totalCarbs + f.getCarbs();
			totalProtein = totalProtein + f.getProtein();
			totalSugar = totalSugar + f.getSugars();
			
			if(f.getCarbs() > highestCarbs.getCarbs()){
				highestCarbs = f;
			}
			
			if(f.getProtein() > highestProtein.getProtein()){
				highestProtein = f;
			}
			
			if(f.getSugars() > highestSugars.getSugars()){
				highestSugars = f;
			}
			
		}
		
		Collections.sort(listFrequentItems, new FrequentItemsComparator());
		
		System.out.println();
		System.out.println("Total calories consumed today: " + totalCaloriesIn);
		System.out.println("Total carbs consumed today: " + totalCarbs);
		System.out.println("Total protein consumed today: " + totalProtein);
		System.out.println("Total sugar consumed today: " + totalSugar);
		System.out.println("Highest in protein: " + highestProtein.getName());
		System.out.println("Highest in carbs: " + highestCarbs.getName());
		System.out.println("Highest in sugar: " + highestSugars.getName());
		System.out.println("Highest in calories: " + listFrequentItems.get(0));
		System.out.println();
		
		Insight insight = new Insight();
		insight.setTotalCaloriesIn(totalCaloriesIn).setTotalCarbs(totalCarbs).setTotalProtein(totalProtein).setTotalSugar(totalSugar).setHighestProteinName(highestProtein.getName())
				.setHighestCarbsName(highestCarbs.getName()).setHighestSugarName(highestSugars.getName()).setHighestCaloriesName(listFrequentItems.get(0).getName());
				
		
		List<FrequentItems> highCalorieFood = getHighCalorieFood(listFrequentItems, totalCaloriesIn);
		insight.setUnhealthyFood(highCalorieFood);
		
		return insight;
	}

	private static List<FrequentItems> getHighCalorieFood(List<FrequentItems> listFrequentItems, double totalCaloriesIn) {
		
		List<FrequentItems> listUnhealthyFood = new ArrayList<FrequentItems>();
		
		for(FrequentItems f : listFrequentItems){
			double percentage = (f.getCalories() * 100) / totalCaloriesIn;
			
			NumberFormat nf = NumberFormat.getInstance();
		    nf.setMinimumFractionDigits(7);
		    String pct = nf.format(percentage);
			
		    if(percentage > THRESHOLD_BAD_FOOD)
		    	listUnhealthyFood.add(f);
		    
			System.out.println(f + "\tPercentage: " + pct);
		}
		
		return listUnhealthyFood;
	}

}