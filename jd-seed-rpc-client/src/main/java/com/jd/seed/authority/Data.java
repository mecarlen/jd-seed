package com.jd.seed.authority;

/**
 * <pre>
 * 业务数据
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:51:00
 */
public interface Data extends Resource {

	Data getParent();

	String getSn();

	DataType getType();

	String getRegulation();

	String getDescr();
}
