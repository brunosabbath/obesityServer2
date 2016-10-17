package com.sbbi.obesity.helpers;

import java.util.Date;

public class DateHelper {

	public static Date sqlToDate(java.sql.Date sqlDate){
		return new Date(sqlDate.getTime());
	}
	
	public static java.sql.Date dateToSql(Date utilDate){
		return new java.sql.Date(utilDate.getTime());
	}
	
}
