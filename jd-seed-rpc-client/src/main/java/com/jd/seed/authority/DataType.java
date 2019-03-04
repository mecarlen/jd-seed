package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 数据类型
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:52:59
 */
public interface DataType extends Entity<Long> {

	String getName();

	String getUnityCode();

	DataType getParent();

	String getSn();

	String getDescr();
}
