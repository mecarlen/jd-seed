package com.jd.seed.util.regex;

/**
 * <pre>
 * 常见的正则表达式
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月2日 下午9:01:48
 */
public enum EnumCommonRegexPattern {

	/** 手机号 13,14,15,17,18 */
	MOBILE("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,1,5-9]))\\d{8}$"),
	/** 座机 027-87113128 */
	PHONE("^0\\d{2,3}-[1-9]\\d{6,7}$"),
	/** IDC-身份证号-18/15位 */
	IDCARDNO("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$"),
	/** 其它证件号码 证件类型:"PSP":护照;"HKM":港澳通行证;"TW1":回乡证;"TW2":台胞证;"OFFICER":军官证;"SOLDIER":士兵证;"SEA":国际海员证;"OTHER":其他 */
	CERTIFICATE("(^[A-Za-z0-9]{1,3}\\**[A-Za-z0-9]{1}$)|(^[A-Za-z0-9]*$)"),
	/** 邮箱 */
	EMAIL("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),
	/** 整数  123456 */
	NUMBER("^\\d+$"),
	/** 中文-简体/繁体 */
	CHINESE("^[(\\u4e00-\\u9fa5)]+$"),
	/** IP 127.0.0.1 */
	IP("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
	final private String pattern;

	private EnumCommonRegexPattern(String pattern) {
		this.pattern = pattern;
	}

	public String pattern() {
		return pattern;
	}

}
