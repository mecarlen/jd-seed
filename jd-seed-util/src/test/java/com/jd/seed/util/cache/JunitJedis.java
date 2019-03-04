package com.jd.seed.util.cache;

import static org.springframework.util.Assert.isTrue;

import javax.annotation.Resource;

import org.junit.Test;

import com.jd.seed.JunitUtilBase;

/**
 * <pre>
 * jedis cache
 * 
 * </pre>
 * 
 * @author mecarlen 2018年8月8日 下午2:42:54
 */
public class JunitJedis extends JunitUtilBase {
	@Resource(name = "cacheUtilsJedis")
	private CacheUtils cacheUtils;
	@Test
	public void set() {
		isTrue(cacheUtils.set("cache", "jedis"), "jedis set failure");
	}
}
