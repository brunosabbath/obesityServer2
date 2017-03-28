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

	public static int getHour() {
		Calendar myCalendar = new GregorianCalendar();
		return myCalendar.HOUR_OF_DAY;
	}

	public static java.sql.Timestamp getBreakfastStart() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 6, 00);
		return dateToSql(myCalendar.getTime());
	}
	
	public static java.sql.Timestamp getBreakfastEnd() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 10, 00);
		return dateToSql(myCalendar.getTime());
	}
	
	public static java.sql.Timestamp getLunchStart() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 10, 00);
		return dateToSql(myCalendar.getTime());
	}
	
	public static java.sql.Timestamp getLunchEnd() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 15, 00);
		return dateToSql(myCalendar.getTime());
	}

	public static java.sql.Timestamp getDinnerStart() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 17, 00);
		return dateToSql(myCalendar.getTime());
	}
	
	public static java.sql.Timestamp getDinnerEnd() {
		Calendar myCalendar = new GregorianCalendar(getYear(), getCurrentMonth(), getTodaysDay(), 22, 00);
		return dateToSql(myCalendar.getTime());
	}
	
}
