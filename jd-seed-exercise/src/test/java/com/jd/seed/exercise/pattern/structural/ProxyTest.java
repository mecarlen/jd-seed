package com.jd.seed.exercise.pattern.structural;

import java.lang.reflect.Proxy;

import org.junit.Test;

import com.jd.seed.exercise.pattern.structural.proxy.ConcreteHello;
import com.jd.seed.exercise.pattern.structural.proxy.Hello;
import com.jd.seed.exercise.pattern.structural.proxy.HelloProxy;


/**
 * <pre>
 * <b>代理测试</b>
 * 
 * </pre>
 * 
 * @author mecarlen.Wang 2020年3月10日 下午4:36:08
 */
public class ProxyTest {

	@Test
	public void dynaProxy() {
		HelloProxy<ConcreteHello> helloProxy = new HelloProxy<>(new ConcreteHello());
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Hello hello = (Hello)Proxy.newProxyInstance(classLoader, new Class[] {Hello.class}, helloProxy);
		System.out.println(hello.sayTo("Tommy"));
	}
}
