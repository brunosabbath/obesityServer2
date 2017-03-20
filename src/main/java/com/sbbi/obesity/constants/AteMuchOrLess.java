package com.sbbi.obesity.constants;

public enum AteMuchOrLess {
	TOO_MUCH (1), TOO_LITTLE (-1), NORMAL (0);
	
	private final int value;
	
	private AteMuchOrLess(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}