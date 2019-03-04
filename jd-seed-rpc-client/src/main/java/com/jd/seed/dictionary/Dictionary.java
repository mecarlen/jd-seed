package com.jd.seed.dictionary;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 字典
 * 
 * name String 名称
 * unityCode String 统一编码
 * </pre>
 * 
 * @author mecarlen 2016年8月11日 下午5:57:41
 */
public interface Dictionary extends Entity<Long> {

	String getUnityCode();

	String getName();
}
