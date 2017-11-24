package com.jd.seed.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 实体接口
 * 
 * </pre>
 * 
 * @author mecarlen 2016年8月9日 下午5:49:04
 */
public interface Entity<ID extends Serializable,V extends ValueObject<ID,?>> {
	/** 实体-状态-在用  1 */
	final public static int STATE_NORMAL=1;
	/** 实体-状态-停用  0*/
	final public static int STATE_DISABLE=0;
	/** 实体-状态-弃用  1*/
	final public static int STATE_DEPRECATED=-1;
	/**
	 * <pre>
	 * 获取Entity唯一标示
	 * 
	 * </pre>
	 * @return ID
	 * */
	ID getId();
	/**
	 * <pre>
	 * 给Entity创建时间赋值方法，在CommonServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * @param createTime Date 创建时间
	 * */
	Entity<ID,V> createTime(Date createTime);
	/**
	 * <pre>
	 * 取Entity创建时间,在CommonServiceImpl用到,此方法其子类必须实现
	 * 
	 * <pre>
	 * */
	Date getCreateTime();
	/**
	 * <pre>
	 * Entity转换成ValueObject方法,在CommonServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * @return V 具体ValueObject类型
	 * */
	V toVO();
	
}
