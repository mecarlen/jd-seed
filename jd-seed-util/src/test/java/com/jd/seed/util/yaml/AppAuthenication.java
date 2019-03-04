package com.jd.seed.util.yaml;

import java.util.List;

/**
 * <pre>
 * junit class
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 上午11:56:42
 */
public class AppAuthenication {
	// appCode
	private String appCode;
	// serviceCode list
	private List<String> sclist;
	// securityKey
	private String securityKey;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public List<String> getSclist() {
		return sclist;
	}

	public void setSclist(List<String> sclist) {
		this.sclist = sclist;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

}
