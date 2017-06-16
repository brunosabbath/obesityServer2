package com.sbbi.obesity.test;

import org.springframework.web.client.RestTemplate;

public class GetRequest {

	public static void main(String[] args) {
		
		RestTemplate template = new RestTemplate();
		String s = template.getForObject("http://localhost:5000/classify?path=/home/bsilva/Desktop/rice.jpg", String.class);
		System.out.println(s);
		
	}
	
}
