package com.sbbi.obesity.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;

@RestController
@RequestMapping("/pictures")
public class PicturesService {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, 
			@RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4) {
		
		
		String s1 = saveImage(file1, "1");
		String s2 = saveImage(file2, "2");
		String s3 = saveImage(file3, "3");
		String s4 = saveImage(file4, "4");
		
		return null;
		
	}

	private String saveImage(MultipartFile file, String s) {
		long timeInMillis = System.currentTimeMillis();
		System.out.println("current time: " + timeInMillis);
		//String imgPath = ImageHelper.buildImagePath(file, timeInMillis);
		String imgPath = ImageHelper.buildImagePath(file, s);
		
		try {
			ImageHelper.transferTo(imgPath, file);
			//return new Response().setData("image uploaded");
			return "image uploaded";
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			return e.getMessage();
		}
		
	}
	
}
