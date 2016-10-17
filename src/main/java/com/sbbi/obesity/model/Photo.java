package com.sbbi.obesity.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the Photo database table.
 * 
 */
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String path;

	//bi-directional many-to-one association to Meal
	private Meal meal;

	public Photo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Meal getMeal() {
		return this.meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

}