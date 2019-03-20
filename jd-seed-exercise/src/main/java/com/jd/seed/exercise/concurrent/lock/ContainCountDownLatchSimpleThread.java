package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * 线程协调器CountDownLatch
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 上午10:24:35
 */
public class ContainCountDownLatchSimpleThread implements Runnable {
	// 计算器
	private CountDownLatch latch;
	private String name;

	public ContainCountDownLatchSimpleThread(CountDownLatch latch, String name) {
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println(name + "正在执行");
			Thread.sleep(3000L);
			System.out.println(name + "执行完毕");
			latch.countDown();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}

}
