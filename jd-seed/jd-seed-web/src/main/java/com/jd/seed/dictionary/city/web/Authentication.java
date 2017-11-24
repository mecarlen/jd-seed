package com.jd.seed.dictionary.city.web;

/**
 * 调用认证权限
 * @author renwei1
 *
 * 2017年10月24日下午6:34:31
 * 佛祖保佑_永无BUG_UTF8
 */
public class Authentication {

	private String appId; // 调用者应用id
	private String appSecret;
	private String version;// 调用接口版本号
	private String timestamp;// 时间戳
	private String nonce;// 随机串
	private String sign;//签名

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
