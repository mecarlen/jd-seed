package com.jd.seed.exercise.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 * 读写分离的互斥锁
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月13日 下午5:29:57
 */
public class ReentrantAndReadWriteLock {
	//
	protected Map<String, String> map = new HashMap<String, String>();
	// 计数器
	protected CountDownLatch latch;
	// 集合点
	protected CyclicBarrier barrier;

	// 初始化
	private void init(int thredCount) {
		latch = new CountDownLatch(thredCount);
		barrier = new CyclicBarrier(thredCount);
	}

	// 计算耗时
	private void calculateTimeCost(long beginTime) {
		try {
			latch.await();
			System.out.println("total time cost " + (System.currentTimeMillis() - beginTime) + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 启动函数
	public void start() {
		int writeThredCount = 100, readThredCount = 400;
		ExecutorService service = Executors.newCachedThreadPool();
		// 计算使用ReentrantReadWriteLock耗时
		init(writeThredCount + readThredCount);
		ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < writeThredCount; ++i)
			service.execute(new WriteTask(reentrantReadWriteLock.writeLock(), this));
		for (int i = 0; i < readThredCount; ++i)
			service.execute(new ReadTask(reentrantReadWriteLock.readLock(), this));
		calculateTimeCost(beginTime);
		// 计算使用ReentrantLock的耗时
		init(writeThredCount + readThredCount);
		ReentrantLock reentrantLock = new ReentrantLock();
		beginTime = System.currentTimeMillis();
		for (int i = 0; i < writeThredCount; ++i)
			service.execute(new WriteTask(reentrantLock, this));
		for (int i = 0; i < readThredCount; ++i)
			service.execute(new ReadTask(reentrantLock, this));
		calculateTimeCost(beginTime);
		service.shutdownNow();
	}

}
