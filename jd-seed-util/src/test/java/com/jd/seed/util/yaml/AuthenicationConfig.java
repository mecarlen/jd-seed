package com.jd.seed.util.yaml;

import java.util.Map;

/**
 * <pre>
 * junit class
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 上午11:56:22
 */
public class AuthenicationConfig {
	private String version;
	private String state;
	private Map<String, AppAuthenication> auths;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, AppAuthenication> getAuths() {
		return auths;
	}

	public void setAuths(Map<String, AppAuthenication> auths) {
		this.auths = auths;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
