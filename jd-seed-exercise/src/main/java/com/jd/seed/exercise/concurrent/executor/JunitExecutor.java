package com.jd.seed.exercise.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * <pre>
 * 线程池
 * 
 * </pre>
 *
 * @author mecarlen 2017年12月5日 下午5:50:45
 */
public class JunitExecutor {

	@Test
	public void singleThreadExecutor(){
		ExecutorService singleEs = Executors.newSingleThreadExecutor();
		singleEs.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("I'm single thread executor");
				
			}
		});
	}
}
