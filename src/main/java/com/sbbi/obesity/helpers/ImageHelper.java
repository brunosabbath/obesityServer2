package com.sbbi.obesity.helpers;

import java.io.IOException;


import org.springframework.web.multipart.MultipartFile;

public class ImageHelper {

	private static final String PATH = "/home/bsilva/Desktop/";
	//private static final String PATH = "/home/public/sbbi09/bruno/obesityMatlab/foodImgs/";
	
	public static String buildImagePath(MultipartFile file, long timeInMillis){
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
	
	
	
}
