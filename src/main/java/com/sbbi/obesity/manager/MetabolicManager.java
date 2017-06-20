package com.sbbi.obesity.manager;

import com.mathworks.toolbox.javabuilder.MWException;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.model.Food;

public class MetabolicManager {

	private double foodArray1[] = new double[4];
	private double foodArray2[] = new double[4];
	private double foodArray3[] = new double[4];
	
	public MetabolicManager() {}
	
	public void setArrayFood1(double[] array) {
		this.foodArray1 = array;
	}

	public void setArrayFood2(double[] array) {
		this.foodArray2 = array;
	}

	public void setArrayFood3(double[] array) {
		this.foodArray3 = array;
	}
	
	public void runMetabolic(){
		
			//Metabolic metabolic = new Metabolic();
			//Object[] checkBoundry = metabolic.integratedModel(1, foodArray1, foodArray2, foodArray3);
			//MWNumericArray result =  (MWNumericArray)checkBoundry[0];
			
			//(number of outputs, input1, input2, input3)
			
			//System.out.println(result.get(1));//on x1, result.get(1) is the result of y 
			//System.out.println(result.get(2));//on x1, result.get(2) is the result of y
			
			//now I have to loop through all  
		
	}
	
}
