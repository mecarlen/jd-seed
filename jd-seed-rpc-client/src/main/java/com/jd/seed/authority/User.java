package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 用户
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午3:59:52
 */
public interface User extends Entity<Long> {
	/** 类型-员工-0 */
	final public static int EMPLOYEE_TYPE_CODE = 0;
	/** 类型-访客-1 */
	final public static int VISTOR_TYPE_CODE = 1;

	String getUnityCode();

	String getFullName();

	String getPassword();

	String getEmail();

	String getMobile();
}
