package com.sbbi.obesity.controller;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.DateHelper;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.manager.MealManager;
import com.sbbi.obesity.manager.UploadedImageManager;
import com.sbbi.obesity.manager.UserManager;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FoodsWeightEstimation;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.Pixels;
import com.sbbi.obesity.model.Prediction;
import com.sbbi.obesity.model.pojo.MealPojo;
import com.sbbi.obesity.model.recommendation.RecommendationRequest;


@RestController
@RequestMapping("/meal")
public class MealController {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public FoodsWeightEstimation saveMeal(@RequestBody Prediction prediction, @PathVariable("id") Integer userId){
		
		MealManager mealManager = null;
		
		try {
			
			Connection connection = ConnectionFactory.getConnection();
			
			mealManager = new MealManager(connection);
			
			UploadedImageManager imageManager = new UploadedImageManager();
			Pixels newPixels = imageManager.getRelationSideImage(prediction.getPath(), prediction.getPixels());
			prediction.setPixels(newPixels);
						
			UserManager userManager = new UserManager(connection);
			double fingerArea = userManager.getUserFingerArea(userId);
			
			//calculate area foods
			double areaFoodLeft = fingerArea * prediction.getPixels().getRelationTopLeft();//food1
			double areaFoodRight = fingerArea * prediction.getPixels().getRelationTopRight();//food2
			double areaFoodBottom = fingerArea * prediction.getPixels().getRelationTopBottom();//food3
			
			double sideFood1 = fingerArea * prediction.getPixels().getRelationSide1();
			double sideFood2 = fingerArea * prediction.getPixels().getRelationSide2();
			double sideFood3 = fingerArea * prediction.getPixels().getRelationSide3();
			
			double volumeFood1 = areaFoodLeft * sideFood1;
			double volumeFood2 = areaFoodRight * sideFood2;
			double volumeFood3 = areaFoodBottom * sideFood3;
			
			//calculate weight
			FoodManager foodManager = new FoodManager(connection);
			Food foodLeft = foodManager.getFood(prediction.getPredictionsFoodLeft().get(0));
			Food foodRight = foodManager.getFood(prediction.getPredictionsFoodRight().get(0));
			Food foodBottom = foodManager.getFood(prediction.getPredictionsFoodBottom().get(0));
			
			
			double estimatedWeightFood1 = (volumeFood1 * getRealWeight(foodLeft)) / getRealVolume(foodLeft);
			double estimatedWeightFood2 = (volumeFood2 * getRealWeight(foodRight)) / getRealVolume(foodRight);
			double estimatedWeightFood3 = (volumeFood3 * getRealWeight(foodBottom)) / getRealVolume(foodBottom);
			//have to get a measurement base (pixels and weight for all food in the database)
			
			foodLeft.changeAmountGrams(estimatedWeightFood1);
			foodRight.changeAmountGrams(estimatedWeightFood2);
			foodBottom.changeAmountGrams(estimatedWeightFood3);
			
			//saveMeal
			long timestamp = DateHelper.getTimestamp(prediction.getPath());
			mealManager.createMeal(foodLeft, foodRight, foodBottom, userId, timestamp, prediction.getTypeMeal());
			
			FoodsWeightEstimation food = new FoodsWeightEstimation(foodLeft, foodRight, foodBottom, prediction.getTypeMeal());
			
			return food;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			mealManager.close();
		}
		
		return null;
	}
	
	private double getRealVolume(Food foodLeft) {
		// TODO Auto-generated method stub
		return 1;
	}

	private double getRealWeight(Food foodLeft) {
		// TODO Auto-generated method stub
		return 1;
	}

	//list meals from a given user
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public List<MealPojo> list(@PathVariable Integer id){
		
	/*@RequestMapping(method = RequestMethod.GET)
	public boolean list(){	
		int userId = 5;*/
		MealManager mealManager = new MealManager();
		List<MealPojo> meal = new ArrayList<MealPojo>();
		
		try {
			mealManager.addConenction(ConnectionFactory.getConnection());
			
			meal = mealManager.list(id);
			
			return meal;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mealManager.close();
		}
		
		return meal;
	}
	
	@RequestMapping(value="/recommendation", method = RequestMethod.POST)
	public List<String> recommendation(@RequestBody RecommendationRequest recommendationRequest) throws SQLException, ParseException{
		
		MealManager mealManager = new MealManager(ConnectionFactory.getConnection());
		List<MealPojo> meal = new ArrayList<MealPojo>();
		
		List<Meal> listTodaysMeals = mealManager.listTodaysMeals(recommendationRequest.getUserId());
		recommendationRequest.setListMeal(listTodaysMeals);
		
		FoodManager foodManager = new FoodManager(ConnectionFactory.getConnection());
		return foodManager.getInsightsAndRecommendation(recommendationRequest.getListMeal(), recommendationRequest.getCaloriesOut());
		
	}
	
}
