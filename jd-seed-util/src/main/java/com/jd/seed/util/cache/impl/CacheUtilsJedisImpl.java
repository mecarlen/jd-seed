package com.jd.seed.util.cache.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.jd.seed.util.cache.CacheUtils;

import redis.clients.jedis.JedisCluster;

/**
 * <pre>
 * 缓存工具 - Redis集群实现
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月2日 下午8:44:29
 */
public class CacheUtilsJedisImpl implements CacheUtils {

	private final static Logger REDIS_LOG = LoggerFactory.getLogger(CacheUtilsJedisImpl.class);
	// jedisClient
	private JedisCluster jedisClient;

	@Override
	public boolean set(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			jedisClient.set(key, value);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jedis-->set failure key:" + key + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean set(String key, Object value) {
		if (StringUtils.isBlank(key) || null == value)
			return false;
		try {
			return set(key, JSON.toJSONString(value));
		} catch (JSONException jsex) {
			String errorMsg = "jedis-->set failure key:" + key + ",object 2 JsonString failue,value=" + value;
			REDIS_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setEx(String key, String value, long seconds) {
		if (StringUtils.isBlank(key) || 0 > seconds)
			return false;
		try {
			jedisClient.setex(key, Long.valueOf(seconds).intValue(), value);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jedis-->setEx failure key:" + key + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
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
			String errorMsg = "jedis-->setEx failure key:" + key + ",object 2 JsonString failue,value=" + value;
			REDIS_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setNX(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			return 1 == jedisClient.setnx(key, value);
		} catch (Exception ex) {
			String errorMsg = "jedis-->setNX failure key:" + key + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean setNX(String key, Object value) {
		if (StringUtils.isBlank(key) || null == value)
			return false;
		try {
			return setNX(key, JSON.toJSONString(value));
		} catch (JSONException jsex) {
			String errorMsg = "jedis-->setNX failure key:" + key + ",object 2 JsonString failue,value=" + value;
			REDIS_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean hSet(String key, String field, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value))
			return false;
		try {
			return 1 == jedisClient.hset(key, field, value);
		} catch (Exception ex) {
			String errorMsg = "jedis-->hSet 设置key失败 failure key:" + key + ",field:" + field + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSet(String key, String field, Object value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == value)
			return false;
		try {
			return hSet(key, field, JSON.toJSONString(value));
		} catch (Exception ex) {
			String errorMsg = "jedis-->hSet failure key:" + key + ",field:" + field + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value))
			return false;
		try {
			return 1 == jedisClient.hsetnx(key, field, value);
		} catch (Exception ex) {
			String errorMsg = "jedis-->hSetNX failure key:" + key + ",field:" + field + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, Object value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == value)
			return false;
		try {
			return hSetNX(key, field, JSON.toJSONString(value));
		} catch (Exception ex) {
			String errorMsg = "jedis-->hSetNX failure key:" + key + ",field:" + field + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean mSet(Map<String, String> values) {
		if (null == values || values.isEmpty())
			return false;
		try {
			String[] keyValues = new String[values.size() * 2];
			int i = 0;
			for (Entry<String, String> entry : values.entrySet()) {
				keyValues[2 * i] = entry.getKey();
				keyValues[2 * i + 1] = entry.getValue();
				i++;
			}
			jedisClient.mset(keyValues);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jedis-->mSet failure ";
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hMSet(String key, Map<String, String> values) {
		if (StringUtils.isBlank(key) || null == values || values.isEmpty())
			return false;
		try {
			String res = jedisClient.hmset(key, values);
			return "OK".equalsIgnoreCase(res);
		} catch (Exception ex) {
			String errorMsg = "jedis-->hMSet failure key:" + key;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public long append(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return -1;
		try {
			return jedisClient.append(key, value);
		} catch (Exception ex) {
			String errorMsg = "jedis-->append failure key:" + key + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return -1;
	}

	@Override
	public Long sAdd(String key, String... values) {
		if (StringUtils.isBlank(key) || null == values || values.length == 0)
			return null;
		try {
			return jedisClient.sadd(key, values);
		} catch (Exception ex) {
			String errorMsg = "jedis-->sAdd failure key:" + key + ",length of values:" + values.length;
			REDIS_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public Long lPush(String key, String... values) {
		if (StringUtils.isBlank(key) || null == values || values.length == 0)
			return null;
		try {
			return jedisClient.lpush(key, values);
		} catch (Exception ex) {
			String errorMsg = "jedis-->lPush failure key:" + key + ",length of value:" + values.length;
			REDIS_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public Long lPush(String key, Object... values) {
		if (StringUtils.isBlank(key) || null == values || values.length == 0)
			return null;
		String[] jsonValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			jsonValues[i] = JSON.toJSONString(values[i]);
		}
		return lPush(key, jsonValues);
	}

	@Override
	public String get(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.get(key);
		} catch (Exception ex) {
			String errorMsg = "jedis-->get failure key:" + key;
			REDIS_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key) || null == clazz)
			return null;
		String stringValue = get(key);
		if (StringUtils.isBlank(stringValue)) {
			REDIS_LOG.warn("jedis--> get failure key:" + key + ",return null");
			return null;
		}
		try {
			return JSON.parseObject(stringValue, clazz);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--><T> T get failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public String hGet(String key, String field) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field))
			return null;
		try {
			return jedisClient.hget(key, field);
		} catch (Exception ex) {
			String errorMsg = "jedis--> hGet failure key:" + key + ",field:" + field;
			REDIS_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public <T> T hGet(String key, String field, Class<T> clazz) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == clazz)
			return null;
		String stringValue = hGet(key, field);
		if (StringUtils.isBlank(stringValue)) {
			REDIS_LOG.warn(
					"jedis--><T> T hGet failure key:" + key + ",field:" + field + ",class:" + clazz + ",return null");
			return null;
		}
		try {
			return JSON.parseObject(stringValue, clazz);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--><T> T hGet failure key:" + key + ",field:" + field + ",class:" + clazz, ex);
		}
		return null;
	}

	@Override
	public Map<String, String> hGetAll(String key) {
		if (StringUtils.isBlank(key))
			return new HashMap<String, String>();
		try {
			return jedisClient.hgetAll(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->hGetAll failure key:" + key, ex);
		}
		return new HashMap<String, String>();
	}

	@Override
	public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key) || null == clazz)
			return new HashMap<String, T>();
		Map<String, String> stringValues = hGetAll(key);
		try {
			Map<String, T> values = new HashMap<String, T>(stringValues.size());
			for (Map.Entry<String, String> entry : stringValues.entrySet()) {
				values.put(entry.getKey(), JSON.parseObject(entry.getValue(), clazz));
			}
			return values;
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--><T> Map<String, T> hGetAll failure key:" + key + ",class:" + clazz, ex);
		}
		return new HashMap<String, T>();
	}

	@Override
	public List<String> mGet(String... keys) {
		if (null == keys || 0 == keys.length)
			return new ArrayList<String>();
		try {
			return jedisClient.mget(keys);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> mGet failure size of keys:" + keys.length, ex);
		}
		return new ArrayList<String>();
	}

	@Override
	public Set<String> sMembers(String key) {
		if (StringUtils.isBlank(key))
			return new HashSet<String>();
		try {
			return jedisClient.smembers(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->sMembers failure key:" + key, ex);
		}
		return new HashSet<String>();
	}

	@Override
	public Long sCard(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.scard(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->sCard failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public Long lLen(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.llen(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->lLen failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public boolean exists(String key) {
		if (StringUtils.isBlank(key))
			return false;
		try {
			return jedisClient.exists(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> exists failure key:" + key, ex);
		}
		return false;
	}

	@Override
	public boolean sIsMember(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			return jedisClient.sismember(key, value);
		} catch (Exception ex) {
			String errorMsg = "jedis--> sIsMember failure key:" + key + ",value:" + value;
			REDIS_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public Long incr(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.incr(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> incr failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public Long incrBy(String key, long step) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.incrBy(key, step);
		} catch (Exception ex) {
			REDIS_LOG.error("jimdb--> incrBy failure key:" + key + ",step:" + step, ex);
		}
		return null;
	}

	@Override
	public Long hIncrBy(String key, String field, long step) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field))
			return null;
		try {
			return jedisClient.hincrBy(key, field, step);
		} catch (Exception ex) {
			REDIS_LOG.error("jimdb--> hIncrBy failure key:" + key + ",field:" + field + ",step:" + step, ex);
		}
		return null;
	}

	@Override
	public Long decr(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.decr(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> decr failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public Long decrBy(String key, long step) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.decrBy(key, step);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> decrBy failure key:" + key + ",step:" + step, ex);
		}
		return null;
	}

	@Override
	public Long sRem(String key, String... values) {
		if (StringUtils.isBlank(key) || null == values || 0 == values.length)
			return null;
		try {
			return jedisClient.srem(key, values);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> srem failure key:" + key + ",size of values:" + values.length, ex);
		}
		return null;
	}

	@Override
	public String lPop(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.lpop(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> lPop failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public <T> T lPop(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key) || null == clazz)
			return null;
		String stringValue = lPop(key);
		if (StringUtils.isBlank(stringValue)) {
			REDIS_LOG.warn("jedis--> lPop failure key:" + key + ",class:" + clazz + ",return null");
			return null;
		}
		try {
			return JSON.parseObject(stringValue, clazz);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> lPop failure key:" + key + ",class:" + clazz, ex);
		}
		return null;
	}

	@Override
	public String rPop(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.rpop(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> rPop failure key:" + key, ex);
		}
		return null;
	}

	@Override
	public <T> T rPop(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key) || null == clazz)
			return null;
		String stringValue = rPop(key);
		if (StringUtils.isBlank(stringValue)) {
			REDIS_LOG.warn("jedis--> rPop failure key:" + key + ",class:" + clazz + ",return null");
			return null;
		}
		try {
			return JSON.parseObject(stringValue, clazz);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> rPop failure key:" + key + ",class:" + clazz, ex);
		}
		return null;
	}

	@Override
	public Long hDel(String key, String... fields) {
		if (StringUtils.isBlank(key) || null == fields || 0 == fields.length)
			return null;
		try {
			return jedisClient.hdel(key, fields);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> hDel failure key:" + key + ",fields:" + fields, ex);
		}
		return null;
	}

	@Override
	public boolean expire(String key, long seconds) {
		if (StringUtils.isBlank(key) || 0 > seconds)
			return false;
		try {
			return 1 == jedisClient.expire(key, Long.valueOf(seconds).intValue());
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> expire failure key:" + key + ",seconds:" + seconds, ex);
		}
		return false;
	}

	@Override
	public boolean expireAt(String key, Date time) {
		if (StringUtils.isBlank(key) || null == time)
			return false;
		try {
			return 1 == jedisClient.expireAt(key, time.getTime() / 1000);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> expire failure key:" + key + ",time:" + time.getTime() / 1000, ex);
		}
		return false;
	}

	@Override
	public boolean del(String key) {
		if (StringUtils.isBlank(key))
			return false;
		try {
			jedisClient.del(key);
			return true;
		} catch (Exception ex) {
			REDIS_LOG.error("jedis--> del failure key:" + key, ex);
		}
		return false;
	}

	@Override
	public boolean lTrim(String key, long start, long end) {
		if (StringUtils.isBlank(key) || start < 0 || end < 0 || start >= end)
			return false;
		try {
			return "OK".equalsIgnoreCase(jedisClient.ltrim(key, start, end));
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->lTrim failure key:" + key + ",start:" + start + ",end:" + end, ex);
		}
		return false;
	}

	@Override
	public String lIndex(String key, long index) {
		if (StringUtils.isBlank(key) || index < 0)
			return null;
		try {
			return jedisClient.lindex(key, index);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->lIndex failure key:" + key + ",index:" + index, ex);
		}
		return null;
	}

	@Override
	public boolean hExists(String key, String field) {
		if (StringUtils.isBlank(key))
			return false;
		try {
			return jedisClient.hexists(key, field);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->hExists failure key:" + key + ",field:" + field, ex);
		}
		return false;
	}

	@Override
	public Long pTtl(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return jedisClient.pttl(key);
		} catch (Exception ex) {
			REDIS_LOG.error("jedis-->pTtl failure key:" + key, ex);
		}
		return null;
	}

	public void setJedisClient(JedisCluster jedisClient) {
		this.jedisClient = jedisClient;
	}
}
