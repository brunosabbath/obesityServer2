package com.sbbi.obesity.helpers;

import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class FoodClassificationHelper {

	public static double predictFoodWeight(double foodVolume, double volumeFoodDatbase, double weightFoodDatabase) {
		double weight = (foodVolume * weightFoodDatabase) / volumeFoodDatbase;
		return weight;
	}

	public static double getFoodWeight(int index) {
		return Constraints.weightFoods[index];
	}

	public static double getFoodVolume(int index) {
		return Constraints.volumeFoods[index];
	}

	public static int getFoodIndex(String string, String[] foods) {
		
		for(int i = 0; i < foods.length; i++){
			if(string.equals(foods[i])){
				return i;
			}
		}
		
		return -1;
	}

	public static double calculateVolume(MWNumericArray areaFood1Obj, double areaFingerPixel, Object[] sideResult1, double fingerArea, double fingerHeight) {
		
		double areaFood1Pixel = (double)areaFood1Obj.get(1);
		double areaFood1 = (fingerArea * areaFood1Pixel)/areaFingerPixel;
		
		MWNumericArray sideResultFinger1Obj = (MWNumericArray)sideResult1[0];
		MWNumericArray sideResultFood1Obj = (MWNumericArray)sideResult1[1];
		
		double fingerSideFood1 = (double) sideResultFinger1Obj.get(1);
		double heightSideFood1 = (double) sideResultFood1Obj.get(1);
		
		double heightFood1 = (heightSideFood1 / fingerSideFood1) * fingerHeight;
		
		double volumeFood1 = areaFood1 * heightFood1;
		
		return volumeFood1;
	}
	
}
