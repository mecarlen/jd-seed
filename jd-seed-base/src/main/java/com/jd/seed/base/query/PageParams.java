package com.jd.seed.base.query;

/**
 * <pre>
 * 分页查询默认参数
 * 
 * </pre>
 * 
 * @author mecarlen 2018年9月30日 下午4:06:12
 */
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

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSlaveIdx() {
		return slaveIdx;
	}

	public void setSlaveIdx(String slaveIdx) {
		this.slaveIdx = slaveIdx;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}
	
	

}
