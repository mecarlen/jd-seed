package com.jd.seed.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * <p>
 * authentication for a network connection
 * </p>
 * 
 * @author metanoia.lang 2012-4-24
 * @author mecarlen 2018年3月15日 下午7:53:26
 */
public class MailAuthenticator extends Authenticator {
	private String	userName	= null;
	private String	password	= null;

	/**
	 * <p>
	 * 设置认证账号
	 * </p>
	 * 
	 * @param userName
	 *            String 用户名
	 * @param password
	 *            String 密码
	 */
	public MailAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * <p>
	 * 获取当前认证对象
	 * </p>
	 * 
	 * @return javax.mail.PasswordAuthentication
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
