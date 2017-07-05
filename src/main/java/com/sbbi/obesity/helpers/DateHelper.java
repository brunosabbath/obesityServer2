package com.sbbi.obesity.helpers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		int i = myCalendar.get(Calendar.HOUR_OF_DAY);
		return i;
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

	public static Timestamp getTodayTimestamp() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		return dateToSql(todayWithZeroTime);
	}

	public static Timestamp getTomorrowTimestamp() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		String format = formatter.format(dt);
		
		Date d = formatter.parse(format);
		
		return dateToSql(d);
	}

	public static long getTimestamp(String path) {
		String str[] = path.split("/");
		return Long.parseLong(str[8].trim());
	}

	public static String timestampToString(Timestamp timestamp) {
		String timestampStr = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss").format(timestamp);
		return timestampStr;
	}
	
}
