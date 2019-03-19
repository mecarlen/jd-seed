package com.jd.seed.exercise.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.lock.ContainCountDownLatchSimpleThread;
import com.jd.seed.exercise.concurrent.lock.ParticipantThread;
import com.jd.seed.exercise.concurrent.lock.VideoConferenceThread;

/**
 * <pre>
 * 程序计数器
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 上午10:32:49
 */
public class CountDownLatchTest {
	@Test
	public void countDown() {
		// 子线程个数
		int threadNum = 10;
		CountDownLatch latch = new CountDownLatch(threadNum);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 1; i <= threadNum; i++) {
			String threadName = "第【" + i + "】线程";
			executor.execute(new ContainCountDownLatchSimpleThread(latch, threadName));
		}

		try {
			System.out.println(threadNum + "个线程正在执行,正在执行线程数:" + latch.getCount());
			latch.await(15, TimeUnit.SECONDS);
			System.out.println(threadNum + "个线程执行完毕");
			System.out.println("正在执行线程数:" + latch.getCount());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void videoConference() {
		int conferenceMembers = 8;
		VideoConferenceThread videoConference = new VideoConferenceThread(conferenceMembers);
		ExecutorService conferenceExecutor = Executors.newCachedThreadPool();
		conferenceExecutor.execute(videoConference);
		// 入会人初始化
		ExecutorService participantExecutor = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= conferenceMembers; i++) {
			String name = "第【"+i+"】位入会人";
			participantExecutor.execute(new ParticipantThread(videoConference, name));
		}

	}
}
