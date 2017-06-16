package com.sbbi.obesity.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sbbi.obesity.dao.MealDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.helpers.DateHelper;
import com.sbbi.obesity.manager.FoodManager;
import com.sbbi.obesity.model.Meal;

public class MealQuery {

	public static void main(String[] args) {

		int userId = 5;

		try {
			Connection connection = ConnectionFactory.getConnection();

			MealDaoImpl mealDao = new MealDaoImpl(connection);

			GregorianCalendar today = new GregorianCalendar(2017, 3 - 1, 8);
			Date todayDate = new Date(today.getTimeInMillis());
			System.out.println(todayDate);
			
			GregorianCalendar tomorrow = new GregorianCalendar(2017, 3 - 1, 9);
			Date tomorrowDate = new Date(tomorrow.getTimeInMillis());
			System.out.println(tomorrowDate);
			
			//mealDao.listTodaysMeals(userId, DateHelper.getTodayTimestamp(), DateHelper.getTomorrowTimestamp());
			List<Meal> listTodaysMeals = mealDao.listTodaysMeals(userId, DateHelper.dateToSql(todayDate), DateHelper.dateToSql(tomorrowDate));
			
			FoodManager foodManager = new FoodManager(ConnectionFactory.getConnection());
			foodManager.getInsightsAndRecommendation(listTodaysMeals, 300);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}/* catch (ParseException e) {
			e.printStackTrace();
		}*/

	}

}