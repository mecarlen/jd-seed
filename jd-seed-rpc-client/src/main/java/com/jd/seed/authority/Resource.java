package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 资源
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:03:29
 */
public interface Resource extends Entity<Long> {
	/** 类型-菜单-0 */
	final public static int MENU_TYPE_CODE = 0;
	/** 类型-数据-1 */
	final public static int DATA_TYPE_CODE = 1;

	String getUnityCode();

	String getName();
}
