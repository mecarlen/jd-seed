package com.jd.seed.base.rpc.security.authentication;

import java.util.List;

/**
 * <pre>
 * API授权
 * 
 * </pre>
 * 
 * @author mecarlen 2018年3月26日 下午9:05:52
 */
public class AppAuthenication {
	// appCode
	private String appCode;
	// service list
	private List<String> servicelist;
	// securityKey
	private String securityKey;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public List<String> getServicelist() {
		return servicelist;
	}

	public void setServicelist(List<String> servicelist) {
		this.servicelist = servicelist;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
}
