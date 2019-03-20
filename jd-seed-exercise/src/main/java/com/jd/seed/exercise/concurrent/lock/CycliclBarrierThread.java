package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <pre>
 * 集结线程
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 上午10:50:31
 */
public class CycliclBarrierThread implements Runnable {
	//集结器
	private CyclicBarrier barrier;
	private String name;
	
	public CycliclBarrierThread(CyclicBarrier barrier, String name) {
		this.barrier = barrier;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println(name + "进入集结");
			barrier.await();
			System.out.println(name + ":达到集结阀值，开始后续任务,"+System.currentTimeMillis());
			Thread.sleep(300);
			System.out.println(name + "执行完毕");
		} catch (InterruptedException e) {
			System.out.println(name+":"+e.getMessage());
		} catch (BrokenBarrierException e) {
			System.out.println(name+":"+e.getMessage());
		} 
		
	}
	
}
