package com.jd.seed.util.xml;

import com.jd.seed.base.SystemException;

/**
 * 
 * @author metanoia.lang 2012-4-25
 */
public class Dom4jUtilsException extends SystemException {
	private static final long	serialVersionUID	= 2849606993520948645L;

	public Dom4jUtilsException() {
		super();
	}

	public Dom4jUtilsException(String message) {
		super(message);
	}

	public Dom4jUtilsException(String message, Throwable cause) {
		super(message, cause);
	}

	public Dom4jUtilsException(Throwable cause) {
		super(cause);
	}
}
