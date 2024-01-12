package com.springlab.biz.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

@Service("logAdvice")
public class LogAdvice {
	
	public void beforeLog() {
		System.out.println("[사전 처리] 실행 전 로깅...");
	}
	
	public void afterReturningLog() {
		System.out.println("[사후 처리] 실행 반환 후 로깅...");
	}
	
	public void afterThrowingLog() {
		System.out.println("[사후 처리] 예외 발생 후 로깅...");
	}
	
	public void afterLog() {
		System.out.println("[사후 처리] 실행 종료 후 로깅...");
	}
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Object result =pjp.proceed();
		
		long end = System.currentTimeMillis();
		System.out.println("[사후 처리] 실행 시간: " + (end-start) + " msec");
		
		return result;
	}
}
