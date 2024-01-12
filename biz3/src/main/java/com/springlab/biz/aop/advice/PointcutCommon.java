package com.springlab.biz.aop.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.springlab.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springlab.biz..*Impl.get*(..))")
	public void getPointcut() {}

}
