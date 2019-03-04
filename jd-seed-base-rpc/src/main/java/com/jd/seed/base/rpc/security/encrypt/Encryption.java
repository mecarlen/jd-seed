package com.jd.seed.base.rpc.security.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 加密
 * 
 * 描述
 * 1、加密成密文
 * 2、部分隐藏，可指定规则，默认为******
 * 3、手机号隐藏中间四位，身份证隐藏后6位等
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月2日 下午5:31:06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Encryption {
	/**
	 * <pre>
	 * 是否加密,注释默认加密
	 * 
	 * </pre>
	 * 
	 * @return boolean
	 */
	boolean encrypt() default true;

	/**
	 * <pre>
	 * 加密类型,默认密文
	 * 
	 * </pre>
	 * 
	 * @return EnumEncryptionMethod
	 */
	EnumEncryptionType type() default EnumEncryptionType.DEFAULT;

}
