package com.sbbi;

import com.mathworks.toolbox.javabuilder.MWException;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.model.pojo.FoodPojo;


public class TestMetabolicIntegration {

	public static void main(String[] args) {
		
		FoodPojo food = new FoodPojo();
		double carbs = food.getCarbohydrate();
		double protein = food.getProtein();
		double sugar = food.getSugar();
		double lipid = food.getLipid();
		
		double foodArray1[] = new double[4];
		double foodArray2[] = new double[4];
		double foodArray3[] = new double[4];
		
		foodArray1[0] = protein;
		foodArray1[1] = lipid;
		foodArray1[2] = carbs;
		foodArray1[3] = sugar;
		
		
			//Metabolic metabolic = new Metabolic();
			
			//(number of outputs, input1, input2, input3)
			/*Object[] checkBoundry = metabolic.integratedModel(1, foodArray1, foodArray2, foodArray3);
			MWNumericArray result =  (MWNumericArray)checkBoundry[0];
			System.out.println(result.get(1));//on x1, result.get(1) is the result of y 
			System.out.println(result.get(2));//on x1, result.get(2) is the result of y
			System.out.println("stop");
			*/
		
		
		
	}
	
}
