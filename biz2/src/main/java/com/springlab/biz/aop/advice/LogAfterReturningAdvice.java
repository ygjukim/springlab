package com.springlab.biz.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

@Component("logAfterReturningAdvice")
public class LogAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println(method.getName() + "() 메소드 실행 후 로그 : return value=" + returnValue);

	}

}
