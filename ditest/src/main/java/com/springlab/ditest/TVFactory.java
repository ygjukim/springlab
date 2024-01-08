package com.springlab.ditest;

public class TVFactory {

	public static TV getTV(String brand) {
		if (brand.equals("samsung")) {
			return new SamsungTV2();
		}
		else if (brand.equals("lg")) {
			return new LgTV2();
		}
		return null;
	}
	
}
