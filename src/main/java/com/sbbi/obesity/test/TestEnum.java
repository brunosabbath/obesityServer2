package com.sbbi.obesity.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.sbbi.obesity.helpers.MealTypeEnum;

public class TestEnum {

	public static void main(String[] args) {
		
		MealTypeEnum dinner = MealTypeEnum.BREAKFAST;
		System.out.println(dinner.getValue());
		
		Calendar myCalendar = new GregorianCalendar();
		System.out.println(myCalendar.HOUR_OF_DAY);
		
	}
}