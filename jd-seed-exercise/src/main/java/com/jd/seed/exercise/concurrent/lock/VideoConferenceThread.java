package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * 视频会议主线程
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 下午1:59:53
 */
public class VideoConferenceThread implements Runnable {
	final private CountDownLatch latch;

	public VideoConferenceThread(int num) {
		latch = new CountDownLatch(num);
	}

	public void arrive(String name) {
		System.out.println(name + "上线");
		latch.countDown();
		System.out.println("还待" + latch.getCount() + "上线");
	}

	@Override
	public void run() {
		synchronized (VideoConferenceThread.class) {
			if (latch.getCount() != 0) {
				System.out.println("视频会议初始" + latch.getCount() + "人");
			}
		}
		try {
			latch.await();
			System.out.println("所有人已上线，会议开始。。。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
