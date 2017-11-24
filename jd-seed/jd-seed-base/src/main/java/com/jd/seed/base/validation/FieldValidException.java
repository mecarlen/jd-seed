package com.jd.seed.base.validation;

import com.jd.seed.base.SystemException;

/**
 * <pre>
 * 字段校验异常
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月22日 下午7:36:44
 */
public class FieldValidException extends SystemException {

	private static final long serialVersionUID = -900653526160127400L;

	public FieldValidException() {
		super();
	}

	public FieldValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FieldValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldValidException(String message) {
		super(message);
	}

	public FieldValidException(Throwable cause) {
		super(cause);
	}
	
}
