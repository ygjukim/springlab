package com.springlab.biz.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service("logAdvice2")
@Aspect
public class LogAdvicewithBinding {

//	@Pointcut("execution(* com.springlab.biz..*Impl.*(..))")
//	public void allPointcut() {}
//	
//	@Pointcut("execution(* com.springlab.biz..*Impl.get*(..))")
//	public void getPointcut() {}
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("[사전 처리] " + method + "() 메소드 호출 Arguments: " + 
				(args[0] != null ? args[0].toString() : "null"));
	}
	
	@AfterReturning(value="PointcutCommon.allPointcut()", returning="returnValue")
	public void afterReturningLog(JoinPoint jp, Object returnValue) {
		String method = jp.getSignature().getName();
		
		System.out.println("[사후 처리] " + method + "() 메소드 반환값: " + 
				(returnValue != null ? returnValue.toString() : "null"));
	}
	
	@AfterThrowing(value="PointcutCommon.allPointcut()", throwing="ex")
	public void afterThrowingLog(JoinPoint jp, Exception ex) {
		String method = jp.getSignature().getName();
		
		System.out.println("[사후 처리] " + method + "() 메소드 예외 발생: " + ex.getMessage());
	}
	
	public void afterLog() {
		System.out.println("[사후 처리] 실행 종료 후 로깅...");
	}
	
	@Around("PointcutCommon.getPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
				
		Object result =pjp.proceed();
		
		stopWatch.stop();
		System.out.println("[사후 처리] " + method + "() 메소드 실행 시간: " + stopWatch.getTotalTimeMillis() + " msec");
		
		return result;
	}
}
