package com.beanbox.test.aop;

import com.beanbox.aop.advice.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: @zyz
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before (Method method , Object[] args , Object target) {
		System.out.println ("拦截方法： "+method.getName ());
	}
}
