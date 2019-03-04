package com.jd.seed.organization;
/**
 * <pre>
 * 职务
 * 
 * 职务是随组织结构定的
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:21:30
 */
public interface Duty {

	Duty getParent();
	Department getDepartment();
}
