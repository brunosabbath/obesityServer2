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
	private final int TOTAL_FOOD_DB = 13;
	private final int TOTAL_FOOD_PLATE = 3;
	
	String foods[] = {"Apple", "Banana", "Blueberry", "Carrot", "Chips", "Grape", "Grilled chicken breast", "Orange", "Peach", "Raspberry", "Rice", "Sandwich bread"};
	
	public ClassificationManager(String[] paths){
		this.paths = paths;
	}

	public ClassificationReturn makePredictions() {
		
		Object result[] = null;
		ClassificationReturn classifResult = new ClassificationReturn();
		
		try {
			
			ClassifyTop classify = new ClassifyTop();
			result = classify.test(3, paths[TOP]);
			
			Object object = result[1];
			
			MWNumericArray r = (MWNumericArray) object;
				
			List<FoodClassification> list = new ArrayList<FoodClassification>();
						
			for(int i = 0; i < foods.length; i++){
				list.add(new FoodClassification(i, foods[i], (double) r.get(i+1)));
			}
			
			Collections.sort(list);
			
			System.out.println("youre eating: " + result[0]);
			for(FoodClassification f : list)
				System.out.println(f);
			
			classifResult.setFood1Str(result[0].toString());
			classifResult.setSuggestionsFood1(list);
			//return new Response().setData("image uploaded");
			System.out.println("image uploaded");
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return classifResult;
	}
	
}
