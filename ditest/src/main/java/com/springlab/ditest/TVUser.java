package com.springlab.ditest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springlab.ditest.config.AppConfig;

public class TVUser {

	public static void main(String[] args) {
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
//		LgTV tv = new LgTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
		
		
//		TV tv = new SamsungTV2();
//		TV tv = new LgTV2();
//		TV tv = TVFactory.getTV(args[0]);
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		TV tv = (TV)context.getBean("tv");
		System.out.println("TV: " + tv.toString());
		
		tv.powerOn();
		tv.soundUp();
		tv.soundDown();
		tv.powerOff();
		
//		context.close();
		
	}
	
}
