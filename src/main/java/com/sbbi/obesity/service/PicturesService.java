package com.sbbi.obesity.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.manager.ClassificationManager;
import com.sbbi.obesity.model.classification.ClassificationReturn;
import com.sbbi.obesity.response.ResponseFood;

@RestController
@RequestMapping("/pictures")
public class PicturesService {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseFood upload(@RequestParam("file1") MultipartFile top, @RequestParam("file2") MultipartFile side1, 
			@RequestParam("file3") MultipartFile side2, @RequestParam("file4") MultipartFile side3) {
		
		String paths[] = new String[4];
		
		if(top != null)
			paths[0] = saveImage(top, "1");
		
		if(top != null)
			paths[1] = saveImage(side1, "2");
		
		if(top != null)
			paths[2] = saveImage(side2, "3");
		
		if(top != null)
			paths[3] = saveImage(side3, "4");
		
		System.out.println("image uploaded, calling manager");
		
		ClassificationManager m = new ClassificationManager(paths);
		return m.makePredictions();
		
	}

	private String saveImage(MultipartFile file, String s) {
		long timeInMillis = System.currentTimeMillis();
		System.out.println("current time: " + timeInMillis);
		//String imgPath = ImageHelper.buildImagePath(file, timeInMillis);
		String imgPath = ImageHelper.buildImagePath(file, s);
		
		try {
			ImageHelper.transferTo(imgPath, file);
			//return new Response().setData("image uploaded");
			return imgPath;
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			return e.getMessage();
		}
		
	}
	
}
