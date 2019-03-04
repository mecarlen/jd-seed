package com.jd.seed.base.rpc.security.authentication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 授权注解
 * 
 * 描述
 * 方法添加此注解表示需要进行权限验证
 * 
 * </pre>
 * 
 * @author mecarlen 2018年3月26日 下午9:15:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Authentication {
	/**
	 * <pre>
	 * 服务编码
	 * 
	 * </pre>
	 * 
	 * @return String
	 */
	String serviceCode();

	/**
	 * <pre>
	 * 是否要鉴权
	 * 
	 * </pre>
	 * 
	 * @return boolean
	 */
	boolean verify() default true;
}
