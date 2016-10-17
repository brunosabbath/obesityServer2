package com.sbbi.obesity.factory;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String DATABASE_URL = "jdbc:mysql://localhost:3306/obesity";
	//private static String DATABASE_URL = "jdbc:mysql://ec2-52-23-173-102.compute-1.amazonaws.com:3306/jiang";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	//private static String USER = "bsilva";
	//private static String PASSWORD = "12345";
	private static String USER = "root";
	private static String PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
}
