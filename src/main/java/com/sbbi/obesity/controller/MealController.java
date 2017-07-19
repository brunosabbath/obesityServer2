package com.sbbi.obesity.controller;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.sbbi.obesity.model.FoodWithWeight;
import com.sbbi.obesity.model.FoodsWeightEstimation;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.Pixels;
import com.sbbi.obesity.model.Prediction;
import com.sbbi.obesity.model.pojo.MealPojo;
import com.sbbi.obesity.model.recommendation.RecommendationRequest;


@RestController
@RequestMapping("/meal")
public class MealController {
	
	private HashMap<String, FoodWithWeight> foodHash;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public FoodsWeightEstimation saveMeal(@RequestBody Prediction prediction, @PathVariable("id") Integer userId){
		
		MealManager mealManager = null;
		
		try {
			
			List<FoodWithWeight> list = loadListFood();
			
			
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
			
			foodLeft.setGrams(estimatedWeightFood1);
			foodRight.setGrams(estimatedWeightFood2);
			foodBottom.setGrams(estimatedWeightFood3);
			
			FoodsWeightEstimation food = new FoodsWeightEstimation(foodLeft, foodRight, foodBottom, prediction.getTypeMeal());
			
			return food;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}/* finally {
			//mealManager.close();
		}*/
		
		return null;
	}
	
	private void loadListFood() {
		
		foodHash = new HashMap<String, FoodWithWeight>();
		
		//weight, volume, food name
		foodHash.put("Apple", new FoodWithWeight(115, 193, "Apple"));
		foodHash.put("Banana", new FoodWithWeight(140, 209, "Banana"));
		foodHash.put("Blueberry", new FoodWithWeight(6, 27, "Blueberry"));
		foodHash.put("Carrot", new FoodWithWeight(20, 48, "Carrot"));
		foodHash.put("Chips", new FoodWithWeight(15, 97, "Chips"));
		foodHash.put("Grape", new FoodWithWeight(5, 28, "Grape"));
		foodHash.put("Orange", new FoodWithWeight(190, 198, "Orange"));
		foodHash.put("Peach", new FoodWithWeight(130, 191, "Peach"));
		foodHash.put("Pear", new FoodWithWeight(135, 201, "Pear"));
		foodHash.put("Raspberry", new FoodWithWeight(8, 29, "Raspberry"));
		foodHash.put("Rice", new FoodWithWeight(180, 320, "Rice"));
		foodHash.put("spring_roll", new FoodWithWeight(25, 124, "spring_roll"));
		foodHash.put("sushi", new FoodWithWeight(50, 105, "sushi"));
		foodHash.put("apple_pie", new FoodWithWeight(110, 302, "apple_pie"));
		foodHash.put("bagel", new FoodWithWeight(60, 99, "bagel"));
		foodHash.put("beans", new FoodWithWeight(25, 99, "beans"));
		foodHash.put("biscuit", new FoodWithWeight(50, 57, "biscuit"));
		foodHash.put("burrito", new FoodWithWeight(200, 265, "burrito"));
		foodHash.put("caesar_salad", new FoodWithWeight(10, 411, "caesar_salad"));
		foodHash.put("cake", new FoodWithWeight(60, 364, "cake"));
		foodHash.put("cereal", new FoodWithWeight(20, 305, "cereal"));
		foodHash.put("cheese", new FoodWithWeight(5, 17, "cheese"));
		foodHash.put("cheesecake", new FoodWithWeight(120, 438, "cheesecake"));
		foodHash.put("chicken_wings", new FoodWithWeight(35, 197, "chicken_wings"));
		foodHash.put("chickpea", new FoodWithWeight(25, 94, "chickpea"));
		foodHash.put("cinnamon_roll", new FoodWithWeight(55, 92, "cinnamon_roll"));
		foodHash.put("cookie", new FoodWithWeight(50, 133, "cookie"));
		foodHash.put("cracker", new FoodWithWeight(10, 43, "cracker"));
		foodHash.put("croissant", new FoodWithWeight(50, 101, "croissant"));
		foodHash.put("cup_cakes", new FoodWithWeight(35, 94, "cup_cakes"));
		foodHash.put("donuts", new FoodWithWeight(60, 101, "donuts"));
		foodHash.put("egg", new FoodWithWeight(50, 733, "egg"));
		foodHash.put("eggplant", new FoodWithWeight(25, 130, "eggplant"));
		foodHash.put("eggs_benedict", new FoodWithWeight(90, 216, "eggs_benedict"));
		foodHash.put("french_fries", new FoodWithWeight(60, 187, "french_fries"));
		foodHash.put("grilled_cheese_sandwich", new FoodWithWeight(75, 276, "grilled_cheese_sandwich"));
		foodHash.put("Grilled_chicken_breast", new FoodWithWeight(25, 199, "Grilled_chicken_breast"));
		foodHash.put("grilled_salmon", new FoodWithWeight(120, 220, "grilled_salmon"));
		foodHash.put("hamburger", new FoodWithWeight(240, 1093, "hamburger"));
		foodHash.put("hotdog", new FoodWithWeight(50, 363, "hotdog"));
		foodHash.put("lasagna", new FoodWithWeight(160, 195, "lasagna"));
		foodHash.put("lobster", new FoodWithWeight(450, 351, "lobster"));
		foodHash.put("marshmallow", new FoodWithWeight(10, 54, "marshmallow"));
		foodHash.put("mashed_potato", new FoodWithWeight(20, 111, "mashed_potato"));
		foodHash.put("muffin", new FoodWithWeight(20, 72, "muffin"));
		foodHash.put("omelet", new FoodWithWeight(80, 130, "omelet"));
		foodHash.put("pancakes", new FoodWithWeight(50, 99, "pancakes"));
		foodHash.put("pasta", new FoodWithWeight(160, 337, "pasta"));
		foodHash.put("pizza", new FoodWithWeight(70, 156, "pizza"));
		foodHash.put("popcorn", new FoodWithWeight(10, 25, "popcorn"));
		foodHash.put("potato", new FoodWithWeight(50, 110, "potato"));
		foodHash.put("pretzel", new FoodWithWeight(30, 45, "pretzel"));
		foodHash.put("quesadilla", new FoodWithWeight(50, 209, "quesadilla"));
		foodHash.put("Sandwich_bread", new FoodWithWeight(35, 281, "Sandwich_bread"));
		foodHash.put("steak", new FoodWithWeight(130, 233, "steak"));
		foodHash.put("strawberry", new FoodWithWeight(50, 45, "strawberry"));
		foodHash.put("taco", new FoodWithWeight(40, 198, "taco"));
		foodHash.put("tofu", new FoodWithWeight(60, 52, "tofu"));
		foodHash.put("tortilla", new FoodWithWeight(40, 31, "tortilla"));
		foodHash.put("waffles", new FoodWithWeight(80, 126, "waffles"));
		
		/*
		List<FoodWithWeight> list = new ArrayList<FoodWithWeight>();
		list.add(new FoodWithWeight(115, 193, "Apple"));
		list.add(new FoodWithWeight(140, 209, "Banana"));
		list.add(new FoodWithWeight(6, 27, "Blueberry"));
		list.add(new FoodWithWeight(20, 48, "Carrot"));
		list.add(new FoodWithWeight(15, 97, "Chips"));
		list.add(new FoodWithWeight(5, 28, "Grape"));
		list.add(new FoodWithWeight(190, 198, "Orange"));
		list.add(new FoodWithWeight(130, 191, "Peach"));
		list.add(new FoodWithWeight(135, 201, "Pear"));
		list.add(new FoodWithWeight(8, 29, "Raspberry"));
		list.add(new FoodWithWeight(180, 320, "Rice"));
		list.add(new FoodWithWeight(25, 124, "spring_roll"));
		list.add(new FoodWithWeight(50, 105, "sushi"));
		list.add(new FoodWithWeight(110, 302, "apple_pie"));
		list.add(new FoodWithWeight(60, 99, "bagel"));
		list.add(new FoodWithWeight(25, 99, "beans"));
		list.add(new FoodWithWeight(50, 57, "biscuit"));
		list.add(new FoodWithWeight(200, 265, "burrito"));
		list.add(new FoodWithWeight(10, 411, "caesar_salad"));
		list.add(new FoodWithWeight(60, 364, "cake"));
		list.add(new FoodWithWeight(20, 305, "cereal"));
		list.add(new FoodWithWeight(5, 17, "cheese"));
		list.add(new FoodWithWeight(120, 438, "cheesecake"));
		list.add(new FoodWithWeight(35, 197, "chicken_wings"));
		list.add(new FoodWithWeight(25, 94, "chickpea"));
		list.add(new FoodWithWeight(55, 92, "cinnamon_roll"));
		list.add(new FoodWithWeight(50, 133, "cookie"));
		list.add(new FoodWithWeight(10, 43, "cracker"));
		list.add(new FoodWithWeight(50, 101, "croissant"));
		list.add(new FoodWithWeight(35, 94, "cup_cakes"));
		list.add(new FoodWithWeight(60, 101, "donuts"));
		list.add(new FoodWithWeight(50, 733, "egg"));
		list.add(new FoodWithWeight(25, 130, "eggplant"));
		list.add(new FoodWithWeight(90, 216, "eggs_benedict"));
		list.add(new FoodWithWeight(60, 187, "french_fries"));
		list.add(new FoodWithWeight(75, 276, "grilled_cheese_sandwich"));
		list.add(new FoodWithWeight(25, 199, "Grilled_chicken_breast"));
		list.add(new FoodWithWeight(120, 220, "grilled_salmon"));
		list.add(new FoodWithWeight(240, 1093, "hamburger"));
		list.add(new FoodWithWeight(50, 363, "hotdog"));
		list.add(new FoodWithWeight(160, 195, "lasagna"));
		list.add(new FoodWithWeight(450, 351, "lobster"));
		list.add(new FoodWithWeight(10, 54, "marshmallow"));
		list.add(new FoodWithWeight(20, 111, "mashed_potato"));
		list.add(new FoodWithWeight(20, 72, "muffin"));
		list.add(new FoodWithWeight(80, 130, "omelet"));
		list.add(new FoodWithWeight(50, 99, "pancakes"));
		list.add(new FoodWithWeight(160, 337, "pasta"));
		list.add(new FoodWithWeight(70, 156, "pizza"));
		list.add(new FoodWithWeight(10, 25, "popcorn"));
		list.add(new FoodWithWeight(50, 110, "potato"));
		list.add(new FoodWithWeight(30, 45, "pretzel"));
		list.add(new FoodWithWeight(50, 209, "quesadilla"));
		list.add(new FoodWithWeight(35, 281, "Sandwich_bread"));
		list.add(new FoodWithWeight(130, 233, "steak"));
		list.add(new FoodWithWeight(50, 45, "strawberry"));
		list.add(new FoodWithWeight(40, 198, "taco"));
		list.add(new FoodWithWeight(60, 52, "tofu"));
		list.add(new FoodWithWeight(40, 31, "tortilla"));
		list.add(new FoodWithWeight(80, 126, "waffles"));
		*/
	}

	private double getRealVolume(Food food) {
		
		FoodWithWeight f = foodHash.get(food.getName());
		
		return f.getVolume();
	}

	private double getRealWeight(Food food) {
		
		FoodWithWeight f = foodHash.get(food.getName());
		
		return f.getWeight();
	}

	//list meals from a given user
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public List<MealPojo> list(@PathVariable Integer id){
	
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
