package com.jd.seed.dictionary;
/**
 * <pre>
 * 字典
 * id Long id
 * name String 名称
 * unityCode String 统一编码
 * state int 状态
 * </pre>
 * 
 * @author mecarlen 2016年8月11日 下午5:57:41
 */
public interface Dictionary {
	Long getId();
	String getUnityCode();
	String getName();
	int getState();
}
