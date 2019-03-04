package com.jd.seed.base.rpc.security.authentication;

import java.util.Map;

/**
 * <pre>
 * 授权配置
 * 
 * </pre>
 * 
 * @author mecarlen 2018年3月27日 下午3:44:18
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
