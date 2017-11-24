package com.jd.seed.util.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 待发送邮件实例类
 * </p>
 * 
 * @author metanoia.lang 2012-4-24
 */
public class SendMailInstance {
	// 发送邮件的服务器的IP和端口　　　
	private String				mailServerHost;
	// 邮件服务端口,如果没有设置，默认为25
	private String				mailServerPort;

	// 登陆邮件发送服务器的用户名和密码
	private String				userName;
	private String				password;
	// 是否需要身份验证　　　
	private boolean				validate	= true;
	// 发送者的地址
	private String				fromAddress;
	// 接收者的地址
	private List<String>		toAddress;
	// 抄送者的地址
	private List<String>		ccAddress;
	// 暗抄者的地址
	private List<String>		bccAddress;

	// 邮件主题　　　
	private String				subject;
	// 邮件的文本内容　　　
	private String				content;
	// 邮件附件
	private Map<String, File>	files;

	private SendMailTemplate	mt;

	public SendMailInstance(SendMailTemplate mt) {
		this.mt = mt;
		subject = this.mt.subjectTemplate();
		content = this.mt.contentTemplate();
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public SendMailInstance setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
		return this;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public SendMailInstance setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public SendMailInstance setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public SendMailInstance setPassword(String password) {
		this.password = password;
		return this;
	}

	public boolean isValidate() {
		return validate;
	}

	public SendMailInstance setValidate(boolean validate) {
		this.validate = validate;
		return this;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public SendMailInstance setFromAddress(String fromAddress)
			throws MailException {
		if (checkAddress(fromAddress)) {
			this.fromAddress = fromAddress;
		} else {
			throw new MailException(
					"SendMailInstance.setFromAddress(fromAddress) fromAddress error:"
							+ fromAddress);
		}
		return this;
	}

	public List<String> getToAddress() {
		return toAddress;
	}

	public SendMailInstance addToAddress(String toAddress) throws MailException {
		if (checkAddress(toAddress)) {
			if (this.toAddress == null) {
				this.toAddress = new ArrayList<String>();
			}
			this.toAddress.add(toAddress);
		} else {
			throw new MailException(
					"SendMailInstance.addToAddress(toAddress) toAddress error:"
							+ toAddress);
		}

		return this;
	}

	public List<String> getCcAddress() {
		return ccAddress;
	}

	public SendMailInstance addCcAddress(String ccAddress) throws MailException {
		if (checkAddress(ccAddress)) {
			if (this.ccAddress == null) {
				this.ccAddress = new ArrayList<String>();
			}
			this.ccAddress.add(ccAddress);
		} else {
			throw new MailException(
					"SendMailInstance.addCcAddress(ccAddress) ccAddress error:"
							+ ccAddress);
		}

		return this;
	}

	public List<String> getBccAddress() {
		return bccAddress;
	}

	public SendMailInstance addBccAddress(String bccAddress)
			throws MailException {
		if (checkAddress(bccAddress)) {
			if (this.bccAddress == null) {
				this.bccAddress = new ArrayList<String>();
			}
			this.bccAddress.add(bccAddress);
		} else {
			throw new MailException(
					"SendMailInstance.addBccAddress(bccAddress) bccAddress error:"
							+ bccAddress);
		}

		return this;
	}

	public String getSubject() {
		return subject;
	}

	public SendMailInstance setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public SendMailInstance paddingSubject(String key, String value) {
		subject = SendMailTemplate.paddingSubject(subject, key, value);
		return this;
	}

	public SendMailInstance clearSubject() {
		subject = mt.subjectTemplate();
		return this;
	}

	public String getContent() {
		return content;
	}

	public SendMailInstance setContent(String contect) {
		content = contect;
		return this;
	}

	public SendMailInstance paddingContent(String key, String value) {
		content = SendMailTemplate.paddingContent(content, key, value);
		return this;
	}

	public SendMailInstance clearContent() {
		content = mt.contentTemplate();
		return this;
	}

	public Map<String, File> getFiles() {
		return files;
	}

	public SendMailInstance putFile(String filename, File file)
			throws MailException {
		if (file == null || !file.exists()) {
			throw new MailException(
					"SendMailInstance.putFile(filename,file) file not found:"
							+ filename);
		}

		if (files == null) {
			files = new HashMap<String, File>();
		}
		files.put(filename, file);
		return this;
	}

	/**
	 * 获取邮件会话
	 * 
	 * @return Properties
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", mailServerHost);
		p.put("mail.smtp.port", mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	private static boolean checkAddress(String mail) {
		if (mail == null) {
			return false;
		}

		String mail_regex = "^*@*\\.\\w+$";
		Pattern mail_pattern = Pattern.compile(mail_regex,
				Pattern.CASE_INSENSITIVE);

		Matcher mail_matcher = mail_pattern.matcher(mail);

		if (mail_matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
}
