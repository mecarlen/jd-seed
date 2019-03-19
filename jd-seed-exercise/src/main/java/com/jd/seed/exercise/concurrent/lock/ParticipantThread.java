package com.jd.seed.exercise.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 参会人员上线报到线程
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月19日 下午2:08:09
 */
public class ParticipantThread implements Runnable {
	//会议
	private VideoConferenceThread videoConference;
	//参与人姓名
	private String name;
	
	public ParticipantThread(VideoConferenceThread videoConference,String name) {
		this.videoConference = videoConference;
		this.name = name;
	}
	
	@Override
	public void run() {
		videoConference.arrive(name);
		try {
			TimeUnit.MICROSECONDS.sleep(Double.valueOf(Math.random()*1000).longValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
