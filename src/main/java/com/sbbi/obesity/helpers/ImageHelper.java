package com.sbbi.obesity.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class ImageHelper {

	private static final String PATH = "/home/bsilva/Desktop/sbbi/obesityApp/images/";
	//private static final String PATH = "/home/public/sbbi09/bruno/obesityMatlab/foodImgs/";
	
	public static String buildImagePath(MultipartFile file, long timeInMillis){
		return PATH + timeInMillis + getExtension(file.getOriginalFilename());
	}
	
	public static String buildImagePath(MultipartFile file, String timeInMillis){
		return PATH + timeInMillis + getExtension(file.getOriginalFilename());
	}
	
	private static String getExtension(String fileName) {
		return fileName.substring(fileName.indexOf('.'), fileName.length());
	}

	public static void transferTo(String imgPath, MultipartFile file) {
		try {
			file.transferTo(new java.io.File(imgPath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String saveImage(MultipartFile file, String imageId, int userId, long currentTimeMillis) {
		
		try {
			
			//creates user folder
			File userDir = new File(PATH + userId);
			//creates meal img folder
			File mealDir = new File(PATH + userId + "/" + currentTimeMillis);
			
			if(!userDir.exists())
				userDir.mkdir();
			
			if(!mealDir.exists())
				mealDir.mkdir();
			
			//build image path
			String imgPath = PATH + userId + "/" + currentTimeMillis + "/" + imageId + ".jpg";
			
			//saves image
			ImageHelper.transferTo(imgPath, file);
			
			return imgPath;
		} catch (Exception e) {
			//return new Response().setError(e.getMessage());
			return e.getMessage();
		}
		
	}
		
}