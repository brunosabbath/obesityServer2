package com.sbbi.obesity.manager;

import com.mathworks.toolbox.javabuilder.MWException;

import sideImage.FoodSide;

public class WeightManager {
	
	private final int AREA_SIDE_1 = 1;
	private final int AREA_SIDE_2 = 2;
	private final int AREA_SIDE_3 = 3;
	private final int START_SIDE_PHOTO_INDEX = 1;
	
	private double[] calculateWeight(String[] paths, String[] predictedFoods){
		
		FoodSide side;
		
		double weights[] = new double[3];
		
		try {
			side = new FoodSide();
			
			for(int i = START_SIDE_PHOTO_INDEX; i < paths.length; i++){
				Object result[] = side.sideImage(2, paths[i]);
			}
			
		} catch (MWException e) {
			e.printStackTrace();
		}
		
		return weights;
	}
	
}
