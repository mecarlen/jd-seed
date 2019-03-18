package com.jd.seed.exercise.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.executor.Sums;

/**
 * <pre>
 * 线程池
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午4:25:48
 */
public class ExecutorsTest {
	
	@Test
	public void fixedThreadPool() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<Long>> results = executor.invokeAll(Arrays.asList(new Sums(0,10),new Sums(10,100L),new Sums(100,10_000),new Sums(10_000,100_000_000)));
		executor.shutdown();
		for(Future<Long> result:results) {
			System.out.println(result.get());
		}
	}
}
