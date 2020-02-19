package com.jd.seed.exercise.concurrent.executor.example1;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * <b>自定义返回值线程</b>
 * 
 * </pre>
 * 
 * @author mecarlen.Wang 2020年2月19日 上午11:16:03
 */
public class SelfCallable implements Callable<Result> {
	private int idx;
	private CountDownLatch countDownLatch;

	public SelfCallable(int idx, CountDownLatch countDownLatch) {
		super();
		this.idx = idx;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public Result call() throws Exception {
		Result rs = null;
		try {
//			System.out.println(idx + "---------" + Thread.currentThread().getName()+",begin:"+System.currentTimeMillis());
			// 睡60ms
			Thread.sleep(200);
			// 组装结果并返回
			rs = new Result();
			rs.setIdx(idx);
			rs.setThreadName(Thread.currentThread().getName());
		} catch(Exception ex) {
			System.out.println(idx + "-----Exception----");
			ex.printStackTrace();
		} finally {
			countDownLatch.countDown();
		}
//		System.out.println(idx+"---------end:"+System.currentTimeMillis());
		return rs;
	}

}
