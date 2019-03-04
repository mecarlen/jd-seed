package com.jd.seed.util.cache;

import javax.annotation.Resource;

import org.junit.Test;
import static org.springframework.util.Assert.isTrue;

import com.jd.seed.JunitUtilBase;

/**
 * <pre>
 * springDataRedis cache 
 * 
 * </pre>
 * 
 * @author mecarlen 2018年8月8日 下午12:29:36
 */
public class JunitSpringDataRedis extends JunitUtilBase {
	@Resource(name = "cacheUtilsSpringDataRedis")
	private CacheUtils cacheUtils;
	
	@Test
	public void set() {
		isTrue(cacheUtils.set("cache", "springDataRedis"), "springDataRedis set failure");
	}
	
	@Test
	public void incr() {
		isTrue(null != cacheUtils.incrBy("num",6), "springDataRedis incr failure");
	}
	
	@Test
	public void descr() {
		isTrue(null!=cacheUtils.decrBy("num",6), "springDataRedis descr failure");
	}
}
