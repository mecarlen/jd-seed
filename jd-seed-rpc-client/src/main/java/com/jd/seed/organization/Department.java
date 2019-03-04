package com.jd.seed.organization;

import java.util.Date;

/**
 * <pre>
 * 部门
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:20:21
 */
public interface Department {
	Long getId();

	String getName();

	String getUnityCode();

	Department getParent();

	String getSn();

	Integer getSequence();

	String getDescr();
	
	Integer getState();
	
	Date getCreateTime();
	
	Date getUpdateTime();
}
