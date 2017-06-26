package com.sbbi.obesity.test;

import org.springframework.web.client.RestTemplate;

public class ClassificationTest {

	public static void main(String[] args) {
		
		String left = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301/left.jpg";
		String right = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301/right.jpg";
		String bottom = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301/bottom.jpg";
		
		RestTemplate template = new RestTemplate();

		String foodLeft = template.getForObject("http://localhost:5000/classify?path=" + left, String.class);
		System.out.println(foodLeft);
		
		String array[] = foodLeft.split("\n");
		/*System.out.println(array[6]);
		System.out.println(array[7]);
		System.out.println(array[8]);
		System.out.println(array[9]);
		System.out.println(array[10]);
		*/
		
		String str1 = getPredictionString(array[6]);
		String str2 = getPredictionString(array[7]);
		String str3 = getPredictionString(array[8]);
		String str4 = getPredictionString(array[9]);
		String str5 = getPredictionString(array[10]);
				
		/*String foodRight = template.getForObject("http://localhost:5000/classify?path=" + right, String.class);
		System.out.println(foodRight);
		
		String foodBottom = template.getForObject("http://localhost:5000/classify?path=" + bottom, String.class);
		System.out.println(foodBottom);
		*/
	}

	private static String getPredictionString(String string) {
		String str = string.substring(75, string.length());
		int position = 0;
		
		for(int i = 0; i < str.length(); i++){
			if('(' == str.charAt(i)){
				position = i;
				i = str.length();
			}
		}
		
		str = str.substring(0, position);
		
		return str.trim();
	}
	
}
