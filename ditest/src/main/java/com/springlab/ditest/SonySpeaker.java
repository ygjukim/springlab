package com.springlab.ditest;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("sony")
@Lazy
public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println(">>> SonySpeaker 객체 생성");
	}

	@Override
	public void soundUp() {
		System.out.println("Sony Speaker : 소리를 높인다.");
	}

	@Override
	public void soundDown() {
		System.out.println("Sony Speaker : 소리를 낮춘다.");
	}

	@Override
	public String toString() {
		return "SonySpeaker";
	}

}
