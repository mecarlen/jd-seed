package com.jd.seed.base.query;

import lombok.Data;

/**
 * <pre>
 * 分页查询默认参数
 * 
 * </pre>
 * 
 * @author mecarlen 2018年9月30日 下午4:06:12
 */
@Data
public class PageParams<T> {
	// pageNo
	private int pageNo = 1;
	// pageSize
	private int pageSize = 20;
	// 分片后缀
	private String slaveIdx;
	// 参数
	private T params;

	public int getStartRow() {
		return pageNo <= 0 ? 0 : (pageNo - 1) * pageSize;
	}

}
