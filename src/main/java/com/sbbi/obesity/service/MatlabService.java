package com.sbbi.obesity.service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/matlab")
public class MatlabService {

	
	@RequestMapping(value="/seg", method = RequestMethod.GET)
	public String segmentation(){
		
		//SumMatlab.segmentation();
		//int goMAtlab = SumMatlab.goMAtlab();
		//return goMAtlab;
		return "ok";
		
	}
	
}
