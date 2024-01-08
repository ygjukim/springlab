package com.springlab.ditest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("tv")
public class SamsungTV2 implements TV {
	
	@Autowired
	@Qualifier("sony")
	Speaker speaker;
	@Value("${samsung.brand}")
	String brand;
	@Value("${samsung.price}")
	int price;
	
	public SamsungTV2() {
		System.out.println(">>> SamsungTV2 객체 생성");
	}
		
	public SamsungTV2(Speaker speaker, String brand, int price) {
		System.out.println(">>> SamsungTV2 객체 생성");
//		speaker = new SonySpeaker();
//		speaker = (IoCContainer).getBean("speaker");
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
		System.out.println("Samsung TV : 전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("Samsung TV : 전원 끈다.");
	}

	@Override
	public void soundUp() {
//		System.out.println("Samsung TV : 소리를 높인다.");
		speaker.soundUp();
	}

	@Override
	public void soundDown() {
//		System.out.println("Samsung TV : 소리를 낮춘다.");
		speaker.soundDown();
	}

	@Override
	public String toString() {
		return "SamsungTV2 [speaker=" + speaker + ", brand=" + brand + ", price=" + price + "]";
	}
	
}
