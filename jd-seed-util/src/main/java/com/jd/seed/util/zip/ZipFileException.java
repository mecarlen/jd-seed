package com.jd.seed.util.zip;

/**
 * @version V1.0
 * @author metanoia.lang 2012-5-30
 */
public class ZipFileException extends RuntimeException {
	private static final long serialVersionUID = -2386175334799557255L;

	public ZipFileException() {
		super();
	}

	public ZipFileException(String message) {
		super(message);
	}

	public ZipFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZipFileException(Throwable cause) {
		super(cause);
	}
}
