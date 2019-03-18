package com.jd.seed.exercise.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.lock.LockSyncTest;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午2:43:48
 */
public class LockTest {
	@Test
	public void lockPrint() {
		LockSyncTest lt = new LockSyncTest();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 10; i++) {
			pool.execute(new Runnable() {

				@Override
				public void run() {
					lt.execute(Thread.currentThread());
				}
			});
		}
	}

	@Test
	public void str() {
		String str1 = "aaa";
		String str2 = "bbb";
		String str4 = str1 + str2;
		String str3 = "aaabbb";
		str4.intern();
		int price = 10_000_000;
		String str5 = "aaa" + "bbb";
		System.out.println(str3 == str4);
		System.out.println(str3 == str4.intern());
		System.out.println(str3 == str5);
	}
}
