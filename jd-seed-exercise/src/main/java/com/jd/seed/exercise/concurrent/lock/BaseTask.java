package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Lock;

/**
 * <pre>
 * 任务基类
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月13日 下午5:31:53
 */
abstract class BaseTask implements Runnable {
	// 锁
	protected Lock lock;
	protected ReentrantAndReadWriteLock rarw;

	public BaseTask(Lock lock, ReentrantAndReadWriteLock rarw) {
		this.lock = lock;
		this.rarw = rarw;
	}

	@Override
	public void run() {
		// 到达集合点之前 等待
		try {
			rarw.barrier.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 锁数据
			lock.lock();
			execute();
			// 睡100ms
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 解锁
			lock.unlock();
			rarw.latch.countDown();
		}
	}

	abstract protected void execute();

}
