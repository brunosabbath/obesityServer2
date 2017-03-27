package com.sbbi.obesity.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	public static Date sqlToDate(java.sql.Date sqlDate){
		return new Date(sqlDate.getTime());
	}
	
	public static java.sql.Timestamp dateToSql(Date utilDate){
		return new java.sql.Timestamp(utilDate.getTime());
	}

	public static int getCurrentMonth() {
		java.util.Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getTodaysDay() {
		java.util.Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getYear() {
		java.util.Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Date getStartDay() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 0, 0);
		return myCalendar.getTime();
	}
	
	public static Date getEndDay() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 23, 59);
		return myCalendar.getTime();
	}
	
}
