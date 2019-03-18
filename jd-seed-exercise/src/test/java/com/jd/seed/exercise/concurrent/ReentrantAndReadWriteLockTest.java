package com.jd.seed.exercise.concurrent;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.lock.ReentrantAndReadWriteLock;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月13日 下午5:59:50
 */
public class ReentrantAndReadWriteLockTest {
	@Test
	public void run() {
		new ReentrantAndReadWriteLock().start();
	}
}
