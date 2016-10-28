package com.sbbi.obesity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.model.classification.FoodClassification;
import com.sbbi.obesity.response.Response;

import test.ClassifyTop;

@RestController
@RequestMapping("/picture")
public class PictureService {
	
	String foods[] = {"Apple", "Banana", "Blueberry", "Carrot", "Chips", "Grape", "Grilled chicken breast", "Orange", "Pear", "Peach", "Raspberry", "Rice", "Sandwich bread"};
	private final int LALBELS = 0;
	private final int SCORES = 1;
	private final int COST = 2;
	private final int AREA_FOOD_1 = 3;
	private final int AREA_FOOD_2 = 4;
	private final int AREA_FOOD_3 = 5;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
		
		long timeInMillis = System.currentTimeMillis();
		System.out.println("current time: " + timeInMillis);
		String imgPath = ImageHelper.buildImagePath(file, timeInMillis);
		
		Object result[] = null;
		
		try {
			ImageHelper.transferTo(imgPath, file);
			ClassifyTop classify = new ClassifyTop();
			result = classify.test(6,imgPath);
			
			Object object = result[SCORES];
			
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
			
			/*System.out.println("youre eating: " + result[0]);
			for(FoodClassification f : list)
				System.out.println(f);*/
			
			//return new Response().setData("image uploaded");
			return "image uploaded";
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			return e.getMessage();
		}
		
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response ping(){
		return new Response().setData("it works");
	}
	
	
}
