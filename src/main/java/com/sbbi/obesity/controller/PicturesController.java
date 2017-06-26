package com.sbbi.obesity.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.manager.ClassificationManager;
import com.sbbi.obesity.manager.UploadedImageManager;
import com.sbbi.obesity.model.Pixels;
import com.sbbi.obesity.model.Prediction;
import com.sbbi.obesity.response.ResponseFood;

@RestController
@RequestMapping("/pictures")
public class PicturesController {
	
	private String basePath = "/home/bsilva/Desktop/sbbi/obesityApp/images/";
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public @ResponseBody Prediction upload(@RequestParam("file1") MultipartFile top, @RequestParam("file2") MultipartFile side1, 
			@RequestParam("file3") MultipartFile side2, @RequestParam("file4") MultipartFile side3, @PathVariable("id") Integer userId) {
		
		String paths[] = new String[4];
		long currentTimeMillis = System.currentTimeMillis();
		
		if(top != null)
			paths[0] = ImageHelper.saveImage(top, "1", userId, currentTimeMillis);
		
		if(side1 != null)
			paths[1] = ImageHelper.saveImage(side1, "2", userId, currentTimeMillis);
		
		if(side2 != null)
			paths[2] = ImageHelper.saveImage(side2, "3", userId, currentTimeMillis);
		
		if(side3 != null)
			paths[3] = ImageHelper.saveImage(side3, "4", userId, currentTimeMillis);
		
		String path = basePath + userId + "/" + currentTimeMillis;
		
		UploadedImageManager uploadManager = new UploadedImageManager();
		Pixels pixels = uploadManager.getRelationTopImage(path + "/1.jpg");
		//pixels = uploadManager.getRelationSideImage(path, pixels);
		
		ClassificationManager manager = new ClassificationManager();
		Prediction classifyImagesFrom = manager.classifyImagesFrom(path);
		classifyImagesFrom.setPath(path);
		classifyImagesFrom.setPixels(pixels);
		
		return classifyImagesFrom;
	}
	
}
