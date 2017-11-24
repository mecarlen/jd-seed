package com.jd.seed.util.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 日期工具异常
 * 
 * </pre>
 * 
 * @author metanoia.lang 2012-4-26
 * @author mecarlen 2014-6-6 下午2:37:53
 */
public class DateUtilsException extends RuntimeException {

	private static final long serialVersionUID = -8716424831361548063L;
	//logger
	private final static Logger log = LoggerFactory.getLogger(DateUtilsException.class);

	public DateUtilsException() {
		super();
	}

	public DateUtilsException(String message) {
		super(message);
		log.error(message);
	}

	public DateUtilsException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}
}
