package com.sbbi.obesity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbbi.obesity.builder.Builder;
import com.sbbi.obesity.model.Food;

public class FoodDaoImpl {

	private Connection conn;
	
	public FoodDaoImpl(Connection conn){
		this.conn = conn;
	}
	
	public void insert(Food food){
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO food (name, energy, protein, lipid, carbohydrate, fiber, sugar, fatty_acids_saturated, fatty_acids_monounsaturated, fatty_acids_polyunsaturated, fatty_acid_trans, cholesterol) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, food.getName());
			ps.setDouble(2, food.getEnergy());
			ps.setDouble(3, food.getProtein());
			ps.setDouble(4, food.getLipid());
			ps.setDouble(5, food.getCarbohydrate());
			ps.setDouble(6, food.getFiber());
			ps.setDouble(7, food.getSugar());
			ps.setDouble(8, food.getFattyAcidsSaturated());
			ps.setDouble(9, food.getFattyAcidsMonounsaturated());
			ps.setDouble(10, food.getFattyAcidsPolyunsaturated());
			ps.setDouble(11, food.getFattyAcidTrans());
			ps.setDouble(12, food.getCholesterol());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Food getByName(String name) {
		
		PreparedStatement ps = null;
		
		Food food = new Food();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM food WHERE name = ?");
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				food = Builder.buildFood(rs);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return food;
		
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
