package com.jd.seed.base;
/**
 * <pre>
 * 业务异常
 * 
 * 描述:
 *  1、非系统异常
 *  2、业务上预见异常
 *  3、非底层框架抛出的异常
 *  4、依赖服务异常
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:39:58
 */
public class BussinessException extends RuntimeException {
	
	private static final long serialVersionUID = -2253612468234756325L;

	public BussinessException() {
		super();
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

}
