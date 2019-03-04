package com.jd.seed.util.security;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * <pre>
 * MD5算法
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月1日 下午4:54:31
 */
public class MD5CoderTest {
	
	@Test
	public void encrypt() throws NoSuchAlgorithmException {
		String str ="seed123";
		String enStr = MD5Coder.entryptMD5String(str.getBytes());
		System.out.println(str+"------>"+enStr);
	}
}
