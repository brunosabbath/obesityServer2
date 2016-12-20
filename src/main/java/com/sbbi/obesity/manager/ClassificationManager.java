package com.sbbi.obesity.manager;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.classification.ClassificationReturn;
import com.sbbi.obesity.model.classification.FoodClassification;
import com.sbbi.obesity.response.ResponseFoodName;

import sideImage.Side;
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

	public ResponseFoodName makePredictions() {
		
		Object result[] = null;
		//ClassificationReturn classifResult = new ClassificationReturn();
		
		ResponseFoodName response = new ResponseFoodName();
		String resultArray1[] = new String[TOTAL_FOOD_DB];
		String resultArray2[] = new String[TOTAL_FOOD_DB];
		String resultArray3[] = new String[TOTAL_FOOD_DB];
		
		try {
			
			ClassifyTop classify = new ClassifyTop();
			//result = classify.test(6, paths[TOP]);
			result = classify.test(7, paths[TOP]);
			
			Object object = result[1];
			
			MWNumericArray areaFood1Obj =  (MWNumericArray)result[3];
			MWNumericArray areaFood2Obj = (MWNumericArray)result[4];
			MWNumericArray areaFood3Obj = (MWNumericArray)result[5];
			MWNumericArray areaFingerObj = (MWNumericArray)result[6];
			
			double areaFood1Pixel = (double)areaFood1Obj.get(1);
			double areaFood2Pixel = (double)areaFood2Obj.get(1);
			double areaFood3Pixel = (double)areaFood3Obj.get(1);
			double areaFingerPixel = (double)areaFingerObj.get(1);
			int fingerArea = 9;//THIS IS MY THUMB
			
			double finalAreaFood1 = (fingerArea * areaFood1Pixel)/areaFingerPixel; 
			double finalAreaFood2 = (fingerArea * areaFood1Pixel)/areaFingerPixel;
			double finalAreaFood3 = (fingerArea * areaFood1Pixel)/areaFingerPixel;
			
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
				resultArray1[i] = scoresFood1.get(i).getFoodName();
				resultArray2[i] = scoresFood2.get(i).getFoodName();
				resultArray3[i] = scoresFood3.get(i).getFoodName();
				//System.out.println(f);
			}
				
			response.setFood1(resultArray1);
			response.setFood2(resultArray2);
			response.setFood3(resultArray3);
			
			Side sideImgResults = new Side();
			
			double fingerSideFood1, fingerSideFood2, fingerSideFood3;
			double heightSideFood1, heightSideFood2, heightSideFood3;
			
			Object sideResult1[] = sideImgResults.sideImage(2, paths[SIDE_1]);
			Object sideResult2[] = sideImgResults.sideImage(2, paths[SIDE_2]);
			Object sideResult3[] = sideImgResults.sideImage(2, paths[SIDE_3]);
			
			MWNumericArray sideResultFinger1Obj = (MWNumericArray)sideResult1[0];
			MWNumericArray sideResultFood1Obj = (MWNumericArray)sideResult1[1];
			
			MWNumericArray sideResultFinger2Obj = (MWNumericArray)sideResult2[0];
			MWNumericArray sideResultFood2Obj = (MWNumericArray)sideResult2[1];
			
			MWNumericArray sideResultFinger3Obj = (MWNumericArray)sideResult3[0];
			MWNumericArray sideResultFood3Obj = (MWNumericArray)sideResult3[1];
			
			fingerSideFood1 = (double) sideResultFinger1Obj.get(1);
			heightSideFood1 = (double) sideResultFood1Obj.get(1);
			
			fingerSideFood2 = (double) sideResultFinger2Obj.get(1);
			heightSideFood2 = (double) sideResultFood2Obj.get(1);
			
			fingerSideFood3 = (double) sideResultFinger3Obj.get(1);
			heightSideFood3 = (double) sideResultFood3Obj.get(1);
			
			//classifResult.setFood1Str(result[0].toString());
			//classifResult.setSuggestionsFood1(list);
			
			//return new Response().setData("image uploaded");
			
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return response;
	}
	
}