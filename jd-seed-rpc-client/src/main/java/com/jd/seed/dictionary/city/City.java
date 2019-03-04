package com.jd.seed.dictionary.city;

import com.jd.seed.dictionary.Dictionary;

/**
 * <pre>
 * 城市
 * 
 * 
 * zhName String 中文名
 * enName String 英文名
 * unityCode String 三字码
 * zhFullPin String 中文全拼
 * zhShortPin String 中文简拼
 * priority int 优先级
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:04:58
 */
public interface City extends Dictionary {

	String getZhName();

	String getEnName();

	String getZhFullPin();

	String getZhShortPin();

	Integer getPriority();
}
