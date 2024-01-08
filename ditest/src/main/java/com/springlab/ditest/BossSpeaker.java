package com.springlab.ditest;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("boss")
@Lazy
public class BossSpeaker implements Speaker {
	
	public BossSpeaker() {
		System.out.println(">>> BossSpeaker 객체 생성");
	}

	@Override
	public void soundUp() {
		System.out.println("Boss Speaker : 소리를 높인다.");
	}

	@Override
	public void soundDown() {
		System.out.println("Boss Speaker : 소리를 낮춘다.");
	}

	@Override
	public String toString() {
		return "BossSpeaker";
	}

}
