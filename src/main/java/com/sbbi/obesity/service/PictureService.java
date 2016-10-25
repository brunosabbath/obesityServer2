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

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.model.FoodMatlab;
import com.sbbi.obesity.response.Response;

import test.ClassifyTop;

@RestController
@RequestMapping("/picture")
public class PictureService {
	
	String foods[] = {"Apple", "Banana", "Blueberry", "Carrot", "Chips", "Grape", "Grilled chicken breast", "Orange", "Peach", "Raspberry", "Rice", "Sandwich bread"};
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
		
		long timeInMillis = System.currentTimeMillis();
		System.out.println("current time: " + timeInMillis);
		String imgPath = ImageHelper.buildImagePath(file, timeInMillis);
		
		Object result[] = null;
		
		try {
			ImageHelper.transferTo(imgPath, file);
			ClassifyTop classify = new ClassifyTop();
			result = classify.test(3,imgPath);
			
			Object object = result[1];
			
			MWNumericArray r = (MWNumericArray) object;
				
			List<FoodMatlab> list = new ArrayList<FoodMatlab>();
						
			for(int i = 0; i < foods.length; i++){
				list.add(new FoodMatlab(foods[i], (double) r.get(i+1)));
			}
			
			Collections.sort(list);
			
			for(FoodMatlab f : list)
				System.out.println(f);
			
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
