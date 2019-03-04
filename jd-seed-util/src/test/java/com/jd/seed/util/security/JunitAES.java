package com.jd.seed.util.security;

import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * <pre>
 * AES算法验证
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月4日 下午2:15:46
 */
public class JunitAES {
	String key = "1qazXSW@";

	@Test
	public void encrypt() throws Exception {
		String source = "java";
		long startTime = new Date().getTime();
		String enStr = AESCoder.encrypt(source, key);
		long endTime = new Date().getTime();
		System.out.println("costTime:" + (endTime - startTime));
		String deStr = AESCoder.decrypt(enStr, key);
		System.out.println(source + "-" + enStr + "-" + deStr);
		Assert.isTrue(source.equals(deStr), "加密算法异常");
	}

	public void decrypt() {

	}
}
