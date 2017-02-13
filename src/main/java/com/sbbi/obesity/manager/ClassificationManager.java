package com.sbbi.obesity.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.Constraints;
import com.sbbi.obesity.helpers.FoodClassificationHelper;
import com.sbbi.obesity.model.Food;
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
	private volatile Connection connection; 
	
	public ClassificationManager(String[] paths){
		this.paths = paths;
		TOTAL_FOOD_DB = 13;
		foods = Constraints.foods;
		fingerArea = MY_FINGER_HEIGHT * MY_FINGER_WIDTH;
	}

	public ResponseFood makePredictions() {
		
		ResponseFood response = new ResponseFood();
		
		String resultArray1[] = new String[TOTAL_FOOD_DB];
		String resultArray2[] = new String[TOTAL_FOOD_DB];
		String resultArray3[] = new String[TOTAL_FOOD_DB];
		
		try {
			
			ClassifyTop classify = new ClassifyTop();
			Object result[] = null;
			result = classify.test(7, paths[TOP]);
			
			Object object = result[1];
			
			MWNumericArray r = (MWNumericArray) object;
			
			List<FoodClassification> scoresFood1 = new ArrayList<FoodClassification>();
			List<FoodClassification> scoresFood2 = new ArrayList<FoodClassification>();
			List<FoodClassification> scoresFood3 = new ArrayList<FoodClassification>();
			
			//add predicted foods in each array
			for(int i = 0, index = 0; index < foods.length; i = i + 3, index++){
				scoresFood1.add(new FoodClassification(index, foods[index], (double) r.get(i+1)));
				scoresFood2.add(new FoodClassification(index, foods[index], (double) r.get(i+2)));
				scoresFood3.add(new FoodClassification(index, foods[index], (double) r.get(i+3)));
			}
			
			//sort collections of predicted food by highest score
			Collections.sort(scoresFood1);
			Collections.sort(scoresFood2);
			Collections.sort(scoresFood3);
			
			//print results only for testing
			for(int i = 0; i < scoresFood1.size(); i++){
				resultArray1[i] = scoresFood1.get(i).getFoodName();
				resultArray2[i] = scoresFood2.get(i).getFoodName();
				resultArray3[i] = scoresFood3.get(i).getFoodName();
				//System.out.println(f);
			}
				
			MWNumericArray areaFood1Obj =  (MWNumericArray)result[3];//gets area of food1
			MWNumericArray areaFood2Obj = (MWNumericArray)result[4];//gets area of food2
			MWNumericArray areaFood3Obj = (MWNumericArray)result[5];//gets area of food3
			MWNumericArray areaFingerObj = (MWNumericArray)result[6];//gets area of finger
			
			double areaFingerPixel = (double)areaFingerObj.get(1);//getPixels from area finger
			
			Side sideImgResults = new Side();
			
			Object sideResult1[] = sideImgResults.sideImage(2, paths[SIDE_1]);//analyzes side image food 1
			Object sideResult2[] = sideImgResults.sideImage(2, paths[SIDE_2]);//analyzes side image food 2
			Object sideResult3[] = sideImgResults.sideImage(2, paths[SIDE_3]);//analyzes side image food 3
			
			//calculate volume from food 1, 2 and 3
			double food1Volume = FoodClassificationHelper.calculateVolume(areaFood1Obj, areaFingerPixel, sideResult1, fingerArea, MY_FINGER_HEIGHT);
			double food2Volume = FoodClassificationHelper.calculateVolume(areaFood2Obj, areaFingerPixel, sideResult2, fingerArea, MY_FINGER_HEIGHT);
			double food3Volume = FoodClassificationHelper.calculateVolume(areaFood3Obj, areaFingerPixel, sideResult3, fingerArea, MY_FINGER_HEIGHT);
			
			int food1Index = FoodClassificationHelper.getFoodIndex(resultArray1[0], foods);
			double volumeFood1Datbase = FoodClassificationHelper.getFoodVolume(food1Index);
			double weightFood1Database = FoodClassificationHelper.getFoodWeight(food1Index);
			
			int food2Index = FoodClassificationHelper.getFoodIndex(resultArray2[0], foods);
			double volumeFood2Datbase = FoodClassificationHelper.getFoodVolume(food2Index);
			double weightFood2Database = FoodClassificationHelper.getFoodWeight(food2Index);
			
			int food3Index = FoodClassificationHelper.getFoodIndex(resultArray3[0], foods);
			double volumeFood3Datbase = FoodClassificationHelper.getFoodVolume(food3Index);
			double weightFood3Database = FoodClassificationHelper.getFoodWeight(food3Index);
			
			double weightFood1 = FoodClassificationHelper.predictFoodWeight(food1Volume, volumeFood1Datbase, weightFood1Database);
			double weightFood2 = FoodClassificationHelper.predictFoodWeight(food2Volume, volumeFood2Datbase, weightFood2Database);
			double weightFood3 = FoodClassificationHelper.predictFoodWeight(food3Volume, volumeFood3Datbase, weightFood3Database);
			
			response.setFood1(resultArray1).setFood2(resultArray2).setFood3(resultArray3)
			.setWeightFood1(weightFood1).setWeightFood2(weightFood2).setWeightFood3(weightFood3);
			
			
			FoodManager foodManager = new FoodManager(createConnection());
			//go to database and get nutrients for predicted food based on their weights
			Food food1 = foodManager.getFood(resultArray1[0], weightFood1);
			Food food2 = foodManager.getFood(resultArray2[0], weightFood2);
			Food food3 = foodManager.getFood(resultArray3[0], weightFood3);
			closeConnection();
			
			//preparing response
			response.setNutrientsFood1(food1).setNutrientsFood2(food2).setNutrientsFood3(food3);
			
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		try {
			Connection connection = ConnectionFactory.getConnection();
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}