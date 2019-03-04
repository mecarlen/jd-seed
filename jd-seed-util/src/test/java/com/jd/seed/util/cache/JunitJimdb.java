package com.jd.seed.util.cache;

import javax.annotation.Resource;

import org.junit.Test;
import static org.springframework.util.Assert.isTrue;

import com.jd.seed.JunitUtilBase;

/**
 * <pre>
 * Jimdb cache
 * 
 * </pre>
 * 
 * @author mecarlen 2018年8月8日 下午2:20:39
 */
public class JunitJimdb extends JunitUtilBase{
	@Resource(name = "cacheUtilsJimdb")
	private CacheUtils cacheUtils;
	@Test
	public void set() {
		isTrue(cacheUtils.set("cache", "jimdb"), "jimdb set failure");
	}
}
