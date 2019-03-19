package com.jd.seed.exercise.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.lock.CycliclBarrierThread;

/**
 * <pre>
 * 线程集结
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 上午10:56:41
 */
public class CyclicBarrierTest {

	@Test
	public void barrier() {
		int barrierNum = 5;
		CyclicBarrier barrier = new CyclicBarrier(barrierNum, new Runnable() {

			@Override
			public void run() {
				System.out.println("集结完毕，当前时间：" + System.currentTimeMillis());
			}
		});

		int threadNum = 15;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= threadNum; i++) {
			String threadName = "第【" + i + "】线程";
			executor.execute(new CycliclBarrierThread(barrier, threadName));
			if (i % 5 == 0) {
				try {
					Thread.sleep(300);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}
}
