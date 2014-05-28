package com.example.myfirstapp;

public enum Attr {
	library, hospital, playground, park, museum, other, address, distance, name, lat, lng;
	
	public static Attr getCat(String s) {
		try{
			Attr cat = valueOf(s.toLowerCase()); 
			return cat;
		} 
		catch(IllegalArgumentException ex) {
			return other;
		}
	}
}

