package com.sbbi.obesity.test;

import com.sbbi.obesity.manager.ClassificationManager;
import com.sbbi.obesity.model.Prediction;

public class ClassificationTest {

	public static void main(String[] args) {
		
		String path = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301";
				
		ClassificationManager manager = new ClassificationManager();
		Prediction classifyImagesFrom = manager.classifyImagesFrom(path);
		System.out.println("done");
		
	}
	
}