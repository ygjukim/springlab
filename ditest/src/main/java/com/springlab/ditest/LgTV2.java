package com.springlab.ditest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("tv")
public class LgTV2 implements TV {

	Speaker speaker;
	String brand;
	
	int price;
	
	public LgTV2() {
		System.out.println(">>> LgTV2 객체 생성");
	}
		
	@Autowired
	public LgTV2(@Qualifier("boss")Speaker speaker, @Value("${lg.brand}")String brand, @Value("${lg.price}")int price) {
		System.out.println(">>> LgTV2 객체 생성");
		this.speaker = speaker;
		this.brand = brand;
		this.price = price;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("LG TV : 전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LG TV : 전원 끈다.");
	}

	@Override
	public void soundUp() {
//		System.out.println("LG TV : 소리를 높인다.");
		speaker.soundUp();
	}

	@Override
	public void soundDown() {
//		System.out.println("LG TV : 소리를 낮춘다.");
		speaker.soundDown();
	}

	@Override
	public String toString() {
		return "LgTV2 [speaker=" + speaker + ", brand=" + brand + ", price=" + price + "]";
	}
}
