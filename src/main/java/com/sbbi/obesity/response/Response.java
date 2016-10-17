package com.sbbi.obesity.response;

import com.sbbi.obesity.model.Food;

public class Response {

	private String error;
	private String data;
	private Food food;
	
	public Response(){
		error = "";
		data = "";
		
	}
	
	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Response setError(String error){
		this.error = error;
		return this;
	}
	
	public Response setData(String data){
		this.data = data;
		return this;
	}

	public Response setData(Food food){
		this.food = food;
		return this;
	}
	
	public String getError() {
		return error;
	}

	public String getData() {
		return data;
	}
	
}
