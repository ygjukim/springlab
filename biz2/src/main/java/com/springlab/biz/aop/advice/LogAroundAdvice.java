package com.springlab.biz.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component("logAroundAdvice")
public class LogAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();

		Object result = invocation.proceed();	
		
		long end = System.currentTimeMillis();
		String message = (end - start) + " msec 경과";
		System.out.println(invocation.getMethod().getName() + "() method: " + message);

		return result;
	}

}
