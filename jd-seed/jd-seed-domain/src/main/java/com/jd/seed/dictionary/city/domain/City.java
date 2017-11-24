package com.jd.seed.dictionary.city.domain;

import java.util.Date;

/**
 * <pre>
 * 城市
 * 
 * id Long id
 * zhName String 中文名
 * enName String 英文名
 * unityCode String 三字码
 * zhFullPin String 中文全拼
 * zhShortPin String 中文简拼
 * priority int 优先级
 * state int 状态 1-在用,0-停用,-1弃用
 * createTime Date 创建时间
 * updateTime Date 更新时间
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:04:58
 */
public interface City {
	Long getId();

	String getZhName();

	String getEnName();

	String getUnityCode();

	String getZhFullPin();

	String getZhShortPin();

	int getPriority();

	int getState();

	Date getCreateTime();

	Date getUpdateTime();
}
