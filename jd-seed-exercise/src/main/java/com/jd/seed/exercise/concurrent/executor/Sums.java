package com.jd.seed.exercise.concurrent.executor;

import java.util.concurrent.Callable;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午4:21:46
 */
public class Sums implements Callable<Long> {
	private long from;
	private long to;

	public Sums(long from, long to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public Long call() throws Exception {
		long acc = 0;
		for (long i = from; i <= to; i++) {
			acc = acc + i;
		}
		return acc;
	}

}
