package com.jd.seed.organization;

import com.jd.seed.authority.User;

/**
 * <pre>
 * 员工
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:21:05
 */
public interface Employee extends User {

	String getSn();

	String getErpAccount();

	String getPhone();

	String getDescr();
}
