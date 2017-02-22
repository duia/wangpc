package com.wpc.test.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJTest {

	@Pointcut("execution(* *.getUsername(..))")
	public void getUsername(){

	}
	
	@Before("getUsername()")
	public void beforeTest(){
		System.out.println("beforeTest...");
	}
	
	@After("getUsername()")
	public void afterTest(){
		System.out.println("afterTest...");
	}
	
	@Around("getUsername()")
	public Object aroundTest(ProceedingJoinPoint point){
		Object obj = null;
		try {
			obj = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
