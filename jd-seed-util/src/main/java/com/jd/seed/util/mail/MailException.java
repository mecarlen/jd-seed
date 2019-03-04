package com.jd.seed.util.mail;

/**
 * <p>
 * 地址错误 附件无法访问 发送失败异常
 * </p>
 * 
 * @author metanoia.lang 2012-5-15
 * @author mecarlen 2018年3月15日 下午7:53:43
 */
public class MailException extends RuntimeException {
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
