package com.jd.seed.util.security;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

/**
 * <pre>
 * RSA
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月8日 下午8:12:47
 */
public class JunitRSA {
	@Test
	public void encrypt() throws Exception {
		String str = "aaaaaa";
		long iniTime = new Date().getTime();
		Map<String, Object> keys = RSACoder.initKey();
		long startTime = new Date().getTime();
		String enstr = RSACoder.encrypt(str, RSACoder.getPublicKey(keys));
		long middleTime = new Date().getTime();
		String destr = RSACoder.decrypt(enstr, RSACoder.getPrivateKey(keys));
		long endTime = new Date().getTime();
		System.out.println(str + "->" + enstr + "->" + destr);
		System.out.println("iniTime:" + (startTime - iniTime) + ",encostTime:" + (middleTime - startTime)
				+ ",decostTime:" + (endTime - middleTime));
	}
}
