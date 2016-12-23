package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.Constraints;
import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.classification.ClassificationReturn;
import com.sbbi.obesity.model.classification.FoodClassification;
import com.sbbi.obesity.response.ResponseFood;

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
	String foods[];
	double fingerArea;
	
	private final double MY_FINGER_WIDTH = 4.5;//THIS IS MY THUMB
	private final double MY_FINGER_HEIGHT = 2;//THIS IS MY THUMB
	
	
	public ClassificationManager(String[] paths){
		this.paths = paths;
		TOTAL_FOOD_DB = 13;
		foods = Constraints.foods;
		fingerArea = MY_FINGER_HEIGHT * MY_FINGER_WIDTH;
	}

	public ResponseFood makePredictions() {
		
		Object result[] = null;
		//ClassificationReturn classifResult = new ClassificationReturn();
		
		ResponseFood response = new ResponseFood();
		String resultArray1[] = new String[TOTAL_FOOD_DB];
		String resultArray2[] = new String[TOTAL_FOOD_DB];
		String resultArray3[] = new String[TOTAL_FOOD_DB];
		
		
		try {
			
			ClassifyTop classify = new ClassifyTop();
			//result = classify.test(6, paths[TOP]);
			result = classify.test(7, paths[TOP]);
			
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
				resultArray1[i] = scoresFood1.get(i).getFoodName();
				resultArray2[i] = scoresFood2.get(i).getFoodName();
				resultArray3[i] = scoresFood3.get(i).getFoodName();
				//System.out.println(f);
			}
				
			MWNumericArray areaFood1Obj =  (MWNumericArray)result[3];
			MWNumericArray areaFood2Obj = (MWNumericArray)result[4];
			MWNumericArray areaFood3Obj = (MWNumericArray)result[5];
			MWNumericArray areaFingerObj = (MWNumericArray)result[6];
			
			double areaFingerPixel = (double)areaFingerObj.get(1);
			
			Side sideImgResults = new Side();
			
			Object sideResult1[] = sideImgResults.sideImage(2, paths[SIDE_1]);
			Object sideResult2[] = sideImgResults.sideImage(2, paths[SIDE_2]);
			Object sideResult3[] = sideImgResults.sideImage(2, paths[SIDE_3]);
			
			double food1Volume = calculateVolume(areaFood1Obj, areaFingerPixel, sideResult1);
			double food2Volume = calculateVolume(areaFood2Obj, areaFingerPixel, sideResult2);
			double food3Volume = calculateVolume(areaFood3Obj, areaFingerPixel, sideResult3);
			
			int food1Index = getFoodIndex(resultArray1[0]);
			double volumeFood1Datbase = getFoodVolume(food1Index);
			double weightFood1Database = getFoodWeight(food1Index);
			
			int food2Index = getFoodIndex(resultArray2[0]);
			double volumeFood2Datbase = getFoodVolume(food2Index);
			double weightFood2Database = getFoodWeight(food2Index);
			
			int food3Index = getFoodIndex(resultArray3[0]);
			double volumeFood3Datbase = getFoodVolume(food3Index);
			double weightFood3Database = getFoodWeight(food3Index);
			
			double weightFood1 = predictFoodWeight(food1Volume, volumeFood1Datbase, weightFood1Database);
			double weightFood2 = predictFoodWeight(food2Volume, volumeFood2Datbase, weightFood2Database);
			double weightFood3 = predictFoodWeight(food3Volume, volumeFood3Datbase, weightFood3Database);
			
			response.setFood1(resultArray1).setFood2(resultArray2).setFood3(resultArray3)
			.setWeightFood1(weightFood1).setWeightFood2(weightFood2).setWeightFood3(weightFood3);
			
			Connection connection = ConnectionFactory.getConnection();
			FoodManager foodManager = new FoodManager(connection);
			
			Food food1 = foodManager.getFood(resultArray1[0], weightFood1);
			Food food2 = foodManager.getFood(resultArray2[0], weightFood2);
			Food food3 = foodManager.getFood(resultArray3[0], weightFood3);
			
			connection.close();
			
			response.setNutrientsFood1(food1).setNutrientsFood2(food2).setNutrientsFood3(food3);
			
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	private double predictFoodWeight(double foodVolume, double volumeFoodDatbase, double weightFoodDatabase) {
		double weight = (foodVolume * weightFoodDatabase) / volumeFoodDatbase;
		return weight;
	}

	private double getFoodWeight(int index) {
		return Constraints.weightFoods[index];
	}

	private double getFoodVolume(int index) {
		return Constraints.volumeFoods[index];
	}

	private int getFoodIndex(String string) {
		
		for(int i = 0; i < foods.length; i++){
			if(string.equals(foods[i])){
				return i;
			}
		}
		
		return -1;
	}

	private double calculateVolume(MWNumericArray areaFood1Obj, double areaFingerPixel, Object[] sideResult1) {
		
		double areaFood1Pixel = (double)areaFood1Obj.get(1);
		double areaFood1 = (fingerArea * areaFood1Pixel)/areaFingerPixel;
		
		MWNumericArray sideResultFinger1Obj = (MWNumericArray)sideResult1[0];
		MWNumericArray sideResultFood1Obj = (MWNumericArray)sideResult1[1];
		
		double fingerSideFood1 = (double) sideResultFinger1Obj.get(1);
		double heightSideFood1 = (double) sideResultFood1Obj.get(1);
		
		double heightFood1 = (heightSideFood1 / fingerSideFood1) * MY_FINGER_HEIGHT;
		
		double volumeFood1 = areaFood1 * heightFood1;
		
		return volumeFood1;
	}
	
}