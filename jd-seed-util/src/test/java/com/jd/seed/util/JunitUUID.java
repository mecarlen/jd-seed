package com.jd.seed.util;

import static org.springframework.util.Assert.isTrue;

import org.junit.Test;

/**
 * <pre>
 * UUID测试
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 上午10:55:54
 */
public class JunitUUID {
	@Test
	public void buildUUID32() {
		String[] uuids = UUID.ID32(2);
		isTrue(uuids.length == 2, "build 2 uuid32 failure");
	}
}
