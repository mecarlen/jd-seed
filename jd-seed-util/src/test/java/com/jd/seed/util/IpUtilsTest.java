package com.jd.seed.util;

import org.junit.Test;

import com.jd.seed.util.http.IpUtils;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月12日 下午2:14:13
 */
public class IpUtilsTest {
	
	@Test
	public void localIp() {
		System.out.println(IpUtils.getLocalIp());
	}
}
