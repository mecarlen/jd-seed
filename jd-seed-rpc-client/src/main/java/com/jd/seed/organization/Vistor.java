package com.jd.seed.organization;

import com.jd.seed.authority.User;

/**
 * <pre>
 * 访客
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:07:27
 */
public interface Vistor extends User{
	/**
	 * <pre>
	 * 证件ID
	 * 
	 * </pre>
	 * */
	String getCardId();
}
