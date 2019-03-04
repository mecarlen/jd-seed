package com.jd.seed.dictionary.city.web;

import java.io.Serializable;

/**
 * <pre>
 * 通用HTTP返回
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月30日 上午10:42:44
 */
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 2622952564325218376L;
	// code
	private int code;
	// 消息
	private String message;
	// 结果
	private T result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
