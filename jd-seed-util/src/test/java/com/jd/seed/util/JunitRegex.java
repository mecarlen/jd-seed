package com.jd.seed.util;

import static org.springframework.util.Assert.isTrue;

import org.junit.Test;

import com.jd.seed.util.regex.EnumCommonRegexPattern;

/**
 * <pre>
 * 正则junit
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月3日 上午10:39:26
 */
public class JunitRegex {

	@Test
	public void regexMobile() {
		isTrue("18186641234".matches(EnumCommonRegexPattern.MOBILE.pattern()), "格式不正确");
	}
	
	@Test
	public void regexMail(){
		isTrue("xl-0968@163.com".matches(EnumCommonRegexPattern.EMAIL.pattern()), "格式不正确");
	}
	
	@Test
	public void regexChinese(){
		isTrue("花儿諨美".matches(EnumCommonRegexPattern.CHINESE.pattern()), "格式不正确");
	}
	@Test
	public void siteOper() {
		//授权
		System.out.println(12|2);
		//撤权
		System.out.println(12&(~4));
		System.out.println(1<<2);
		System.out.println(1<<3);
	}
}
