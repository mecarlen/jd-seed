package com.jd.seed.util.security;

import org.junit.Test;

/**
 * <pre>
 * DES加密算法验证
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月3日 下午5:32:07
 */
public class JunitDES {

	@Test
	public void encrypt() {
		String key = "c503580232444d73bf6f292f965d4132";

		try {
			String enStr = DESCoder.encrypt("aaa", key);
			System.out.println("enStr------->" + enStr);
			String deStr = DESCoder.decrypt(enStr, key);
			System.out.println("deStr------->" + deStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
