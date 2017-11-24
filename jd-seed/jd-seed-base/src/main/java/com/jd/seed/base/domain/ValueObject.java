package com.jd.seed.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 值对象
 * 
 * </pre>
 * 
 * @param<Id> ValueObject唯一标示类型
 * @param<E> 具体Entity类型
 * 
 * @author mecarlen 2016年8月9日 下午5:49:18
 */
public interface ValueObject<ID extends Serializable, E extends Entity<ID, ?>> {
	/**
	 * <pre>
	 * 给ValueObject唯一标示赋值方法,在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @param<Id> ValueObject唯一标示类型
	 */
	ValueObject<ID, E> id(ID id);

	/**
	 * <pre>
	 * 给ValueObject更新时间赋值方法，在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @param updateTime
	 *            Date 更新时间
	 */
	ValueObject<ID, E> updateTime(Date updateTime);

	/**
	 * <pre>
	 * 取ValueObject更新时间是否为空，在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * <pre>
	 */
	Date getUpdateTime();

	/**
	 * <pre>
	 * ValueObject转换成Entity方法,在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @return E 具体Entity类型
	 */
	E toEntity();
}
