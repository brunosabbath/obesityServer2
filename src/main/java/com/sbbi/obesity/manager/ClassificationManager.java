package com.sbbi.obesity.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.classification.ClassificationReturn;
import com.sbbi.obesity.model.classification.FoodClassification;

import test.ClassifyTop;

public class ClassificationManager {
	
	private String paths[];
	private final int TOP = 0;
	private final int SIDE_1 = 1;
	private final int SIDE_2 = 2;
	private final int SIDE_3 = 3;
	private final int TOTAL_FOOD_DB;
	private final int TOTAL_FOOD_PLATE = 3;
	
	String foods[] = {"Apple", "Banana", "Blueberry", "Carrot", "Chips", "Grape", "Grilled chicken breast", "Orange", "Pear", "Peach", "Raspberry", "Rice", "Sandwich bread"};
	
	public ClassificationManager(String[] paths){
		this.paths = paths;
		TOTAL_FOOD_DB = foods.length;
	}

	public String[] makePredictions() {
		
		Object result[] = null;
		//ClassificationReturn classifResult = new ClassificationReturn();
		
		String resultArray[] = new String[TOTAL_FOOD_DB];
		
		try {
			
			ClassifyTop classify = new ClassifyTop();
			result = classify.test(6, paths[TOP]);
			
			Object object = result[1];
			
			MWNumericArray r = (MWNumericArray) object;
				
			List<FoodClassification> scoresFood1 = new ArrayList<FoodClassification>();
			List<FoodClassification> scoresFood2 = new ArrayList<FoodClassification>();
			List<FoodClassification> scoresFood3 = new ArrayList<FoodClassification>();
						
			for(int i = 0, index = 0; index < foods.length; i = i + 3, index++){
				scoresFood1.add(new FoodClassification(index, foods[index], (double) r.get(i+1)));
				scoresFood2.add(new FoodClassification(index, foods[index], (double) r.get(i+2)));
				scoresFood3.add(new FoodClassification(index, foods[index], (double) r.get(i+3)));
			}
			
			Collections.sort(scoresFood1);
			Collections.sort(scoresFood2);
			Collections.sort(scoresFood3);
			
			//System.out.println("youre eating: " + result[0]);
			for(int i = 0; i < scoresFood1.size(); i++){
				resultArray[i] = scoresFood1.get(i).getFoodName();
				//System.out.println(f);
			}
				
			
			//classifResult.setFood1Str(result[0].toString());
			//classifResult.setSuggestionsFood1(list);
			
			//return new Response().setData("image uploaded");
			System.out.println("image uploaded");
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return resultArray;
	}
	
}
