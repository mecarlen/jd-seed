package com.jd.seed.base.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 字段校验注解
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月22日 下午7:32:58
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValid {
	/** 非空限制 */
	boolean notNull() default false;

	/** 最小长度 */
	int minLength() default 0;

	/** 最大长度 */
	int maxLength() default Integer.MAX_VALUE;

	/** 最小值 */
	String min() default "";

	/** 最大值 */
	String max() default "";
	
	/** 离散值域 */
	String[] valueRange() default {};

	/** 正则匹配 */
	String regex() default "";
}
