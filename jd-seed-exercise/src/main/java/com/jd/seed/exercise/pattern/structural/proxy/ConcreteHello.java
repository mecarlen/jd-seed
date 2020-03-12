package com.jd.seed.exercise.pattern.structural.proxy;

/**
 * <pre>
 * <b>具体say hello</b>
 * 
 * </pre>
 * 
 * @author mecarlen.Wang 2020年3月10日 下午4:24:26
 */
public class ConcreteHello implements Hello{

	@Override
	public String sayTo(String who) {
		return "Hello,"+who;
	}
	
}
