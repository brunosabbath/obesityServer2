package com.sbbi.obesity.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbbi.obesity.dao.FoodDaoImpl;
import com.sbbi.obesity.factory.ConnectionFactory;
import com.sbbi.obesity.model.Food;

public class CreateDatabase {

	public static void main(String[] args) {
		
		Connection connection = null;
		
		List<Food> list = createFood();
		
		try {
			connection = ConnectionFactory.getConnection();
			FoodDaoImpl dao = new FoodDaoImpl(connection);
			
			for(Food f : list)
				dao.insert(f);
			
			System.out.println("database created");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static List<Food> createFood() {
		
		Food apple = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2122?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=apple&ds=Standard+Reference
		apple.setName("Apple").setEnergy(52).setProtein(0.26).setLipid(0.17).setCarbohydrate(13.81).setFiber(2.4).setSugar(10.39)
		.setFattyAcidsSaturated(0.028).setFattyAcidsMonounsaturated(0.007).setFattyAcidsPolyunsaturated(0.051).setFattyAcidTrans(0).setCholesterol(0);
		
		Food banana = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2159?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=banana&ds=Standard+Reference
		banana.setName("Banana").setEnergy(89).setProtein(1.09).setLipid(0.33).setCarbohydrate(22.84).setFiber(2.6).setSugar(12.23)
		.setFattyAcidsSaturated(0.112).setFattyAcidsMonounsaturated(0.032).setFattyAcidsPolyunsaturated(0.073).setFattyAcidTrans(0).setCholesterol(0);
		
		Food blueberry = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2166?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=blueberry&ds=Standard+Reference
		blueberry.setName("Blueberry").setEnergy(57).setProtein(0.74).setLipid(0.33).setCarbohydrate(14.49).setFiber(2.4).setSugar(9.96)
		.setFattyAcidsSaturated(0.028).setFattyAcidsMonounsaturated(0.047).setFattyAcidsPolyunsaturated(0.146).setFattyAcidTrans(0).setCholesterol(0);
		
		Food carrot = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2901?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=carrot&ds=
		carrot.setName("Carrot").setEnergy(41).setProtein(0.93).setLipid(0.24).setCarbohydrate(9.58).setFiber(2.8).setSugar(4.74)
		.setFattyAcidsSaturated(0.037).setFattyAcidsMonounsaturated(0.014).setFattyAcidsPolyunsaturated(0.117).setFattyAcidTrans(0).setCholesterol(0);
		
		Food chicken = new Food();//taken from google - chicken meat - no energy, lipid, fattyacidtrans found
		chicken.setName("Grilled chicken breast").setProtein(27).setCarbohydrate(0).setFiber(0).setSugar(0)
		.setFattyAcidsSaturated(3.8).setFattyAcidsMonounsaturated(5).setFattyAcidsPolyunsaturated(3).setCholesterol(88);
		
		Food chips = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/6362?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=chips&ds=Standard+Reference
		chips.setName("Chips").setEnergy(532).setProtein(6.39).setLipid(33.98).setCarbohydrate(53.83).setFiber(3.1).setSugar(0.33)
		.setFattyAcidsSaturated(3.400).setFattyAcidsMonounsaturated(18.963).setFattyAcidsPolyunsaturated(8.282).setFattyAcidTrans(0.084).setCholesterol(0);
		
		Food grape = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2240?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=grape&ds=Standard+Reference
		grape.setName("Grape").setEnergy(67).setProtein(0.63).setLipid(0.35).setCarbohydrate(17.15).setFiber(0.9).setSugar(16.25)
		.setFattyAcidsSaturated(0.114).setFattyAcidsMonounsaturated(0.014).setFattyAcidsPolyunsaturated(0.102).setFattyAcidTrans(0).setCholesterol(0);
		
		Food orange = new Food();//from usda - no sugar - https://ndb.nal.usda.gov/ndb/foods/show/2288?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=orange&ds=Standard+Reference
		orange.setName("Orange").setEnergy(63).setProtein(1.30).setLipid(0.30).setCarbohydrate(15.50).setFiber(0.9)
		.setFattyAcidsSaturated(0.035).setFattyAcidsMonounsaturated(0.055).setFattyAcidsPolyunsaturated(0.060).setFattyAcidTrans(0).setCholesterol(0);
		
		Food peach = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2311?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=fg&order=desc&qlookup=peach&ds=Standard+Reference
		peach.setName("Peach").setEnergy(39).setProtein(0.91).setLipid(0.25).setCarbohydrate(9.54).setFiber(1.5).setSugar(8.39)
		.setFattyAcidsSaturated(0.019).setFattyAcidsMonounsaturated(0.067).setFattyAcidsPolyunsaturated(0.086).setFattyAcidTrans(0).setCholesterol(0);
		
		Food pear = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2326?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=pear&ds=Standard+Reference
		pear.setName("Pear").setEnergy(57).setProtein(0.36).setLipid(0.14).setCarbohydrate(15.23).setFiber(3.1).setSugar(9.75)
		.setFattyAcidsSaturated(0.022).setFattyAcidsMonounsaturated(0.084).setFattyAcidsPolyunsaturated(0.094).setFattyAcidTrans(0).setCholesterol(0);
		
		Food raspberry = new Food();//from usda - https://ndb.nal.usda.gov/ndb/foods/show/2374?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=raspberry&ds=Standard+Reference
		raspberry.setName("Raspberry").setEnergy(52).setProtein(1.20).setLipid(0.65).setCarbohydrate(11.94).setFiber(6.5).setSugar(4.42)
		.setFattyAcidsSaturated(0.019).setFattyAcidsMonounsaturated(0.064).setFattyAcidsPolyunsaturated(0.375).setFattyAcidTrans(0).setCholesterol(0);
		
		Food rice = new Food();//from usda -fatty acid trans not found - https://ndb.nal.usda.gov/ndb/foods/show/6522?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=white+rice&ds=Standard+Reference
		rice.setName("Rice").setEnergy(97).setProtein(2.02).setLipid(0.19).setCarbohydrate(21.09).setFiber(1).setSugar(0.05)
		.setFattyAcidsSaturated(0.039).setFattyAcidsMonounsaturated(0.070).setFattyAcidsPolyunsaturated(0.069).setCholesterol(0);
		
		Food sandwichBread = new Food();//from usda - cholesterol not found - https://ndb.nal.usda.gov/ndb/foods/show/8173?fgcd=&manu=&lfacet=&format=&count=&max=50&offset=&sort=default&order=asc&qlookup=sandwich+bread&ds=Standard+Reference
		sandwichBread.setName("Sandwich bread").setEnergy(298).setProtein(5.40).setLipid(8.02).setCarbohydrate(51.15).setFiber(5.5).setSugar(11.30)
		.setFattyAcidsSaturated(0.629).setFattyAcidsMonounsaturated(4.721).setFattyAcidsPolyunsaturated(1.957).setFattyAcidTrans(0.023);
		
		List<Food> list = new ArrayList<Food>();
		
		list.add(banana);
		list.add(blueberry);
		list.add(carrot);
		list.add(chicken);
		list.add(chips);
		list.add(grape);
		list.add(orange);
		list.add(peach);
		list.add(pear);
		list.add(raspberry);
		list.add(rice);
		list.add(sandwichBread);
		
		return list;
	}
	
}
