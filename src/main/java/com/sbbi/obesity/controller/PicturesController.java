package com.sbbi.obesity.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.manager.ClassificationManager;
import com.sbbi.obesity.response.ResponseFood;

@RestController
@RequestMapping("/pictures")
public class PicturesController {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseFood upload(@RequestParam("file1") MultipartFile top, @RequestParam("file2") MultipartFile side1, 
			@RequestParam("file3") MultipartFile side2, @RequestParam("file4") MultipartFile side3, @RequestBody Integer id) {
		
		//System.out.println(id);
		
		String paths[] = new String[4];
		
		if(top != null)
			paths[0] = ImageHelper.saveImage(top, "1");
		
		if(side1 != null)
			paths[1] = ImageHelper.saveImage(side1, "2");
		
		if(side2 != null)
			paths[2] = ImageHelper.saveImage(side2, "3");
		
		if(side3 != null)
			paths[3] = ImageHelper.saveImage(side3, "4");
		
		System.out.println("image uploaded, calling manager");
		
		ClassificationManager m = new ClassificationManager(paths);
		
		/*ResponseFood response = m.makePredictions();
		
		MetabolicManager metabolic = MetabolicBuilder.build(response);
		metabolic.runMetabolic();*/
		
		return m.makePredictions();
		
	}
	
}
