package com.sbbi.obesity.insights;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbbi.obesity.constants.AteMuchOrLess;
import com.sbbi.obesity.helpers.FrequentItemsHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.FrequentItemsComparator;
import com.sbbi.obesity.model.Insight;
import com.sbbi.obesity.model.Meal;

public class InsightsMethod {

	//The Ideal Daily Caloric Deficit: 20% below maintenance level per day. 20% of 2500
	//http://www.acaloriecounter.com/diet/how-many-calories-should-i-eat-per-day-to-lose-weight/
	
	private static String percentageResult = "";
	private static String insight = "";
	private static String answer = "";
	private static final double THRESHOLD_BAD_FOOD = 35;
	private static final double DEFICIT_FACTOR = 0.2;
	//for 2 days
	private static final double CALORIE_MAINTENANCE_LEVEL = 5600;
	//fod 1 day
	//private static final double AVERAGE_CALORIES = 2500;
	
	public static AteMuchOrLess ateMuchOrLess(double totalCaloriesIn, double totalCaloriesOut) {
		answer = "";
		double energyBalance = totalCaloriesIn - totalCaloriesOut;
		
		DecimalFormat df = new DecimalFormat("#.00");
		energyBalance = Double.parseDouble(df.format(energyBalance).replace(',', '.'));
		totalCaloriesOut = Double.parseDouble(df.format(totalCaloriesOut).replace(',', '.'));
		
		System.out.println("Energy balance: " + energyBalance);
		
		if(tooMuch(energyBalance)){
			System.out.println("eating too much, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories");
			answer = "eating too much, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories";
			return AteMuchOrLess.TOO_MUCH;
		}
		else if(tooLess(energyBalance, totalCaloriesOut, totalCaloriesIn)){
			System.out.println("eating too little, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories");
			answer = "eating too little, you have consumed around " + totalCaloriesIn + " calories, but you have burned " + totalCaloriesOut + " calories";
			return AteMuchOrLess.TOO_LITTLE;
		}
		else{
			System.out.println("good food habits, " + totalCaloriesIn + " calories in and " + totalCaloriesOut + " calories out");
			answer = "good food habits, " + totalCaloriesIn + " calories in and " + totalCaloriesOut + " calories out";
			return AteMuchOrLess.NORMAL;
		}
			
		
	}

	public static String getAnswer(){
		return answer;
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
	
	public static List<String> week(List<Meal> myMealList, double totalCaloriesOut) {
		List<FrequentItems> listFrequentItems = FrequentItemsHelper.listFrequentItems(myMealList);//frequent items
		double totalCaloriesIn = FrequentItemsHelper.calculateCaloriesIn(listFrequentItems);//calories in
		
		Insight insight = getInsights(listFrequentItems, totalCaloriesIn, totalCaloriesOut);
		
		return new Recommendation().getRecommendation(insight, listFrequentItems, myMealList, totalCaloriesIn, totalCaloriesOut);
	}
	
	private static Insight getInsights(List<FrequentItems> listFrequentItems, double totalCaloriesIn, double totalCaloriesOut) {
		insight = "";
		double totalCarbs = 0;
		double totalProtein = 0;
		double totalSugar = 0;
		FrequentItems highestCarbs = new FrequentItems();
		FrequentItems highestProtein = new FrequentItems();
		FrequentItems highestSugars = new FrequentItems();
		
		DecimalFormat df = new DecimalFormat("#.00");
		
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
		
		totalCarbs = Double.parseDouble(df.format(totalCarbs).replace(',', '.'));
		totalProtein = Double.parseDouble(df.format(totalProtein).replace(',', '.'));
		totalSugar = Double.parseDouble(df.format(totalSugar).replace(',', '.'));
		totalCaloriesIn = Double.parseDouble(df.format(totalCaloriesIn).replace(',', '.'));
		
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
		
		insight = "Total calories consumed today: " + totalCaloriesIn + "\nTotal carbs consumed today: " + totalCarbs + "\nTotal protein consumed today: " + totalProtein + "\nTotal sugar consumed today: " + totalSugar + "\nHighest in protein: " + highestProtein.getName() + "\nHighest in carbs: " + highestCarbs.getName() + "\nHighest in sugar: " + highestSugars.getName() + "\nHighest in calories: " + listFrequentItems.get(0);     
		
		Insight insight = new Insight();
		insight.setTotalCaloriesIn(totalCaloriesIn).setTotalCarbs(totalCarbs).setTotalProtein(totalProtein).setTotalSugar(totalSugar).setHighestProteinName(highestProtein.getName())
				.setHighestCarbsName(highestCarbs.getName()).setHighestSugarName(highestSugars.getName()).setHighestCaloriesName(listFrequentItems.get(0).getName());
				
		
		List<FrequentItems> highCalorieFood = getHighCalorieFood(listFrequentItems, totalCaloriesIn);//prints
		insight.setUnhealthyFood(highCalorieFood);
		insight.setFrequentFood(listFrequentItems);
		
		return insight;
	}

	public static String getInsight(){
		return insight;
	}
	
	private static List<FrequentItems> getHighCalorieFood(List<FrequentItems> listFrequentItems, double totalCaloriesIn) {
		
		List<FrequentItems> listUnhealthyFood = new ArrayList<FrequentItems>();
		percentageResult = "";
		
		for(FrequentItems freqFood : listFrequentItems){
			double percentage = (freqFood.getCalories() * 100) / totalCaloriesIn;
			
			DecimalFormat df = new DecimalFormat("#.00");
			percentage = Double.parseDouble(df.format(percentage).replace(',', '.'));
			
			NumberFormat nf = NumberFormat.getInstance();
		    nf.setMinimumFractionDigits(2);
		    String pct = nf.format(percentage);
			
		    
		    
		    if(percentage > THRESHOLD_BAD_FOOD || freqFood.getGrade() == 'Y' || freqFood.getGrade() == 'R')
		    	listUnhealthyFood.add(freqFood);

		    freqFood.formatOutput();
		    
			System.out.println(freqFood + "\tPercentage: " + pct);
			
			percentageResult += freqFood.getName() + " " + pct + "\n";
		}
		
		return listUnhealthyFood;
	}
	
	public static String getPercentageResult(){
		return percentageResult;
	}

}