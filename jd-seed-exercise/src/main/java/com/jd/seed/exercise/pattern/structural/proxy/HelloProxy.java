package com.jd.seed.exercise.pattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <pre>
 * <b></b>
 * 
 * </pre>
 * 
 * @author mecarlen.Wang 2020年3月10日 下午4:26:41
 */
public class HelloProxy<T extends Hello> implements InvocationHandler {
	private T target;
	
	public HelloProxy(T target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return target.sayTo(String.valueOf(args[0]));
	}

}
