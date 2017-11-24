package com.jd.seed.util.cache.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.jd.seed.util.cache.CacheUtils;

/**
 * <pre>
 * 缓存工具 - jedis实现
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月23日 下午5:52:00
 */
public class CacheUtilsJedisImpl implements CacheUtils {
	private final static Logger JEDIS_LOGGER = LoggerFactory.getLogger(CacheUtilsJedisImpl.class);
	// redis springTemplate
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean set(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->set 设置key失败 key:" + key + ",value:" + value;
			JEDIS_LOGGER.error(errorMsg, ex);
			return false;
		}
	}

	@Override
	public boolean set(String key, Object value) {
		if (StringUtils.isBlank(key) || null == value)
			return false;
		try {
			return set(key, JSON.toJSONString(value));
		} catch (JSONException jsex) {
			String errorMsg = "jimdb-->set 设置key失败 key:" + key + ",object 2 JsonString failue,value=" + value;
			JEDIS_LOGGER.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setEx(String key, String value, long seconds) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value) || 0 > seconds)
			return false;

		try {
			redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->setEx 设置key失败 key:" + key + ",value:" + value;
			JEDIS_LOGGER.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean setEx(String key, Object value, long seconds) {
		if (StringUtils.isBlank(key) || null == value || 0 > seconds)
			return false;
		try {
			return setEx(key, JSON.toJSONString(value), seconds);
		} catch (JSONException jsex) {
			String errorMsg = "jimdb-->setEx 设置key失败 key:" + key + ",object 2 JsonString failue,value=" + value;
			JEDIS_LOGGER.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setNX(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setNX(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hSet(String key, String field, String value) {
		redisTemplate.opsForHash().put(key, field, value);
		return false;
	}

	@Override
	public boolean hSet(String key, String field, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, String value) {
		redisTemplate.opsForHash().putIfAbsent(key, field, value);
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mSet(Map<String, String> values) {
		redisTemplate.opsForValue().multiSet(values);
		return false;
	}

	@Override
	public boolean hMSet(String key, Map<String, String> values) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long append(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long sAdd(String key, String... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lPush(String key, String... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hGet(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T hGet(String key, String field, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> hGetAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> mGet(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> sMembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sCard(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lLen(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sIsMember(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incrBy(String key, long step) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hIncrBy(String key, String field, long step) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sRem(String key, String... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lPop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hDel(String key, String... fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean expire(String key, long seconds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean expireAt(String key, Date time) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean del(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void lTrim(String key, long start, long end) {
		// TODO Auto-generated method stub

	}

	@Override
	public String lIndex(String key, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hExists(String key, String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long pTtl(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

}
