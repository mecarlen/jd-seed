package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * lock同步测试
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午2:37:32
 */
public class LockSyncTest {
	// lock
	private Lock lock = new ReentrantLock();

	public void execute(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println("线程：" + thread.getName() + ",获取了锁");
				Thread.sleep(1L);
				System.out.println("线程：" + thread.getName() + ",我休息会儿");
			} catch (Exception ex) {
			} finally {
				System.out.println("线程：" + thread.getName() + ",释放了锁");
				lock.unlock();
			}
		} else {
			try {
				while (!lock.tryLock()) {
					System.out.println("线程：" + thread.getName() + ",锁已被占用，稍后我再来");
					Thread.sleep(1L);
				}
				System.out.println("线程：" + thread.getName() + ",终于获取了锁");
			} catch (Exception ex) {

			} finally {
				System.out.println("线程：" + thread.getName() + ",我也忙完了，锁释放了啊");
				lock.unlock();
			}
		}
	}
}
