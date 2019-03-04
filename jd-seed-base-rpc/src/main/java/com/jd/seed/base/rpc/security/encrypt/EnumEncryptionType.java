package com.jd.seed.base.rpc.security.encrypt;
/**
 * <pre>
 * 加密方式
 * 
 * ENCRYPTION 对称加密-可逆
 * ENMOBILE 隐藏部分-不可逆-手机号码
 * ENIDCARDNO 隐藏部分-不可逆-身份证号
 * ENCERTIFICATE 隐藏部分-不可逆-其它证件号
 * ENDEFAULT 隐藏所有-不可逆-默认
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月2日 下午6:03:41
 */
public enum EnumEncryptionType {
	/** 加密 */
	ENCRYPT,
	/** 手机号码隐藏 */
	MOBILE,
	/** 身份证号隐藏 */
	IDCARDNO,
	/** 邮箱隐藏 */
	EMAIL,
	/** 其它证件号隐藏 */
	CERTIFICATE,
	/** 默认隐藏所有 */
	DEFAULT
}
