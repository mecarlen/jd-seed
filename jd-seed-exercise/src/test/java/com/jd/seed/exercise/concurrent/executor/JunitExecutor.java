package com.jd.seed.exercise.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.executor.example1.Result;
import com.jd.seed.exercise.concurrent.executor.example1.SelfCallable;

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
	public void singleThreadExecutor() {
		ExecutorService singleEs = Executors.newSingleThreadExecutor();
		singleEs.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("I'm single thread executor");

			}
		});
	}

	/**
	 * <pre>
	 * <b>带返回值的并发</b>
	 * 
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void example1() throws InterruptedException {
//		ExecutorService fixPool = Executors.newFixedThreadPool(10);
		ExecutorService selfPool = new ThreadPoolExecutor(25, 50, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1000),new ThreadPoolExecutor.DiscardPolicy());
		int requestSum = 50;
		final CountDownLatch countDownLatch = new CountDownLatch(requestSum);
		List<Future<Result>> rslist = new ArrayList<>();
		System.out.println(">>>begin:" + System.currentTimeMillis());
		for (int idx = 1; idx <= requestSum; idx++) {
			rslist.add(selfPool.submit(new SelfCallable(idx, countDownLatch)));
		}
		// 计时开始，限时100ms
		countDownLatch.await(300, TimeUnit.MILLISECONDS);
		System.out.println(">>>end:" + System.currentTimeMillis());
		// 关闭线程池
		selfPool.shutdown();

		rslist.forEach(rs -> {
			try {
				Result res = rs.get();
				if (res != null) {
					System.out.println(res.getIdx() + ">>>" + res.getThreadName());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
}
