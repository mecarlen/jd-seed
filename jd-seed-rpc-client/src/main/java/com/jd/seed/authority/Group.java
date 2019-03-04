package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 分组
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:54:14
 */
public interface Group extends Entity<Long> {

	String getName();

	String getUnityCode();

	String getDescr();

}
