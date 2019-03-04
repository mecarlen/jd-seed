package com.jd.seed.util.date;

import java.util.Date;

import org.junit.Test;

/**
 * <pre>
 * 日期测试类
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午3:20:07
 */
public class JunitDate {

	@Test
	public void minusDate(){
		Date startTime = DateConvertor.str2Date("2018-03-29 15:12:45");
		Date endTime = DateConvertor.str2Date("2018-03-29 15:12:50");
		System.out.println(endTime.getTime()-startTime.getTime());
	}
}
