package com.jd.seed.util.mail;

import com.jd.seed.base.SystemException;

/**
 * <p>
 * 地址错误 附件无法访问 发送失败异常
 * </p>
 * 
 * @author metanoia.lang 2012-5-15
 */
public class MailException extends SystemException {
	private static final long serialVersionUID = -8779371827323747466L;

	public MailException() {
		super();
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailException(Throwable cause) {
		super(cause);
	}

}
