package com.jd.seed.util.cache.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.jd.jim.cli.Cluster;
import com.jd.seed.util.cache.CacheUtils;

/**
 * <pre>
 * 缓存工具 - Jimdb实现
 * </pre>
 * 
 * @author mecarlen 2017年11月23日 下午5:25:06
 */
public class CacheUtilsJimdbImpl implements CacheUtils {
	private final static Logger JIMDB_LOG = LoggerFactory.getLogger(CacheUtilsJimdbImpl.class);
	// jimdb client
	private Cluster jimdbClient;

	@Override
	public boolean set(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			jimdbClient.set(key, value);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->set 设置key失败 key:" + key + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
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
			String errorMsg = "jimdb-->set 设置key失败 key:" + key + ",object 2 JsonString failue,value=" + value;
			JIMDB_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setEx(String key, String value, long seconds) {
		if (StringUtils.isBlank(key) || 0 > seconds)
			return false;
		try {
			jimdbClient.setEx(key, value, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->setEx 设置key失败 key:" + key + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
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
			JIMDB_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean setNX(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return false;
		try {
			return jimdbClient.setNX(key, value);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->setNX 设置key失败 key:" + key + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
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
			String errorMsg = "jimdb-->setNX 设置key失败 key:" + key + ",object 2 JsonString failue,value=" + value;
			JIMDB_LOG.error(errorMsg, jsex);
		}
		return false;
	}

	@Override
	public boolean hSet(String key, String field, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value))
			return false;
		try {
			return jimdbClient.hSet(key, field, value);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->hSet 设置key失败 key:" + key + ",field:" + field + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSet(String key, String field, Object value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == value)
			return false;
		try {
			return jimdbClient.hSet(key, field, JSON.toJSONString(value));
		} catch (Exception ex) {
			String errorMsg = "jimdb-->hSet 设置key失败 key:" + key + ",field:" + field + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean mSet(Map<String, String> values) {
		if (null == values || values.isEmpty())
			return false;
		try {
			jimdbClient.mSetString(values);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->mSet 失败 ";
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hMSet(String key, Map<String, String> values) {
		if (StringUtils.isBlank(key) || null == values || values.isEmpty())
			return false;
		try {
			jimdbClient.hMSet(key, values);
			return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb-->hMSet 失败 key=" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value))
			return false;
		try {
			return jimdbClient.hSetNX(key, field, value);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->hSetNX 设置key失败 key:" + key + ",field:" + field + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hSetNX(String key, String field, Object value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == value)
			return false;
		try {
			return jimdbClient.hSetNX(key, field, JSON.toJSONString(value));
		} catch (Exception ex) {
			String errorMsg = "jimdb-->hSetNX 设置key失败 key:" + key + ",field:" + field + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public long append(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return -1;
		try {
			return jimdbClient.append(key, value);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->append 设置key失败 key:" + key + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return -1;
	}

	@Override
	public Long sAdd(String key, String... values) {
		if (StringUtils.isBlank(key) || null == values || values.length == 0)
			return null;
		try {
			return jimdbClient.sAdd(key, values);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->sAdd 设置key失败 key:" + key + ",length of values:" + values.length;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public Long lPush(String key, String... values) {
		if (StringUtils.isBlank(key) || null == values || values.length == 0)
			return null;
		try {
			return jimdbClient.lPush(key, values);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->lPush 设置key失败 key:" + key + ",length of value:" + values.length;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public void lTrim(String key, long start, long end) {
		if (StringUtils.isBlank(key) || start < 0 || end < 0 || start >= end)
			return;
		try {
			jimdbClient.lTrim(key, start, end);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->lTrim 截取失败 key:" + key + ",start:" + start + ",end:" + end;
			JIMDB_LOG.error(errorMsg, ex);
		}

	}

	@Override
	public String lIndex(String key, final long index) {
		if (StringUtils.isBlank(key) || index < 0)
			return null;
		try {
			return jimdbClient.lIndex(key, index);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->lIndex 获取失败 key:" + key + ",index:" + index;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return null;
	}

	@Override
	public String get(String key) {
		String data = null;
		if (StringUtils.isBlank(key))
			return data;
		try {
			data = jimdbClient.get(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb-->get key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key) || null == clazz)
			return null;
		String jsonString = "";
		StringBuilder errorMsg = new StringBuilder("jimdb--><T> T get key:" + key + ",jsonString:");
		try {
			jsonString = get(key);
			errorMsg.append(jsonString);
			if (StringUtils.isNotBlank(jsonString)) {
				return JSON.parseObject(jsonString, clazz);
			}
			JIMDB_LOG.warn(errorMsg.toString());
		} catch (Exception ex) {
			JIMDB_LOG.error(errorMsg.toString(), ex);
		}
		return null;
	}

	@Override
	public String hGet(String key, String field) {
		String data = null;
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field))
			return data;
		try {
			data = jimdbClient.hGet(key, field);
		} catch (Exception ex) {
			jimdbClient.del(key);
			String errorMsg = "jimdb--> hGet key:" + key + ",field:" + field;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public <T> T hGet(String key, String field, Class<T> clazz) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || null == clazz)
			return null;
		StringBuilder errorMsg = new StringBuilder(
				"jimdb--> hGet object key:" + key + ",field:" + field + ",clazz:" + clazz);
		try {
			String objJsonStr = jimdbClient.hGet(key, field);
			errorMsg.append(",objJsonStr:" + objJsonStr);
			if (StringUtils.isNotBlank(objJsonStr)) {
				return JSON.parseObject(objJsonStr, clazz);
			}
			JIMDB_LOG.warn(errorMsg.toString());
		} catch (Exception ex) {
			JIMDB_LOG.error(errorMsg.toString(), ex);
		}
		return null;
	}

	@Override
	public Map<String, String> hGetAll(String key) {
		Map<String, String> data = new HashMap<String, String>();
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data.putAll(jimdbClient.hGetAll(key));
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hGetAll key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
		Map<String, T> data = new HashMap<String, T>();
		if (StringUtils.isBlank(key) || null == clazz) {
			return data;
		}
		Map<String, String> jsonData = hGetAll(key);
		try {
			for (Map.Entry<String, String> entry : jsonData.entrySet()) {
				data.put(entry.getKey(), JSON.parseObject(entry.getValue(), clazz));
			}
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hGetAll Object key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public List<String> mGet(String... keys) {
		List<String> data = new ArrayList<String>();
		if (null == keys || 0 == keys.length) {
			return data;
		}
		try {
			data.addAll(jimdbClient.mGet(keys));
		} catch (Exception ex) {
			String errorMsg = "jimdb--> mGet size of keys:" + keys.length;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Set<String> sMembers(String key) {
		Set<String> data = new HashSet<String>();
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data.addAll(jimdbClient.sMembers(key));
		} catch (Exception ex) {
			String errorMsg = "jimdb--> sMembers key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long sCard(String key) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.sCard(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> scard key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long lLen(String key) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.lLen(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> lLen key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public boolean exists(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		try {
			return jimdbClient.exists(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> exists key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean hExists(String key, String field) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		try {
			return jimdbClient.hExists(key, field);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hExists key:" + key + ",field=" + field;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean sIsMember(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return false;
		}
		try {
			return jimdbClient.sIsMember(key, value);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> sIsMember key:" + key + ",value:" + value;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public Long incr(String key) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.incr(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> incr key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long incrBy(String key, long step) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.incrBy(key, step);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> incr key:" + key + ",step:" + step;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long hIncrBy(String key, String field, long step) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.hIncrBy(key, field, step);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hIncrBy key:" + key + ",field:" + field + ",step:" + step;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long decr(String key) {
		Long data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.decr(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hIncrBy key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long sRem(String key, String... values) {
		Long data = null;
		if (StringUtils.isBlank(key) || null == values || 0 == values.length) {
			return data;
		}
		try {
			data = jimdbClient.sRem(key, values);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> srem key:" + key + ",size of values:" + values.length;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public String lPop(String key) {
		String data = null;
		if (StringUtils.isBlank(key)) {
			return data;
		}
		try {
			data = jimdbClient.lPop(key);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> lPop key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public Long hDel(String key, String... fields) {
		Long data = null;
		if (StringUtils.isBlank(key) || null == fields || 0 == fields.length) {
			return data;
		}
		try {
			data = jimdbClient.hDel(key, fields);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> hDel key:" + key + ",fields:" + fields;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return data;
	}

	@Override
	public boolean expire(String key, long seconds) {
		if (StringUtils.isBlank(key) || 0 > seconds) {
			return false;
		}
		try {
			return jimdbClient.expire(key, seconds, TimeUnit.SECONDS);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> expire key:" + key + ",seconds:" + seconds;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean expireAt(String key, Date time) {
		if (StringUtils.isBlank(key) || null == time) {
			return false;
		}
		try {
			return jimdbClient.expireAt(key, time);
		} catch (Exception ex) {
			String errorMsg = "jimdb--> expireAt key:" + key + ",time:" + time;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public boolean del(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		try {
			Long result = jimdbClient.del(key);
			if (result > 0)
				return true;
		} catch (Exception ex) {
			String errorMsg = "jimdb--> del key:" + key;
			JIMDB_LOG.error(errorMsg, ex);
		}
		return false;
	}

	@Override
	public long pTtl(String key) {
		if (StringUtils.isBlank(key)) {
			return -1L;
		}

		try {
			Long result = jimdbClient.pTtl(key);

			if (result != null) {
				return result.longValue();
			}
		} catch (Exception e) {
			JIMDB_LOG.error("jimdb--> ttl key:" + key, e);
		}

		return -1L;
	}

	public void setJimdbClient(Cluster jimdbClient) {
		this.jimdbClient = jimdbClient;
	}
}
