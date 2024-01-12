package com.springlab.biz.aop.advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component("logAfterThrowingAdvice")
public class LogAfterThrowingAdvice implements ThrowsAdvice {

	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		
		System.out.println("예외 발생: " + e.getMessage());
		
	}
	
}
