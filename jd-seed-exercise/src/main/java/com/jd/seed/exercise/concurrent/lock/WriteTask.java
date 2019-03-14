package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.locks.Lock;

/**
 * <pre>
 * 读任务
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月13日 下午5:45:05
 */
class WriteTask extends BaseTask {
	
	public WriteTask(Lock lock,ReentrantAndReadWriteLock rarw) {
		super(lock, rarw);
	}

	@Override
	protected void execute() {
		rarw.map.put("test", "test case");
	}

}
