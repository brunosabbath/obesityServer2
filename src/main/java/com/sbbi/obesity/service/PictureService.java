package com.sbbi.obesity.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.response.Response;

@RestController
@RequestMapping("/picture")
public class PictureService {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		
		long timeInMillis = System.currentTimeMillis();
		System.out.println("current time: " + timeInMillis);
		String imgPath = ImageHelper.buildImagePath(file, timeInMillis);
		
		try {
			ImageHelper.transferTo(imgPath, file);
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
