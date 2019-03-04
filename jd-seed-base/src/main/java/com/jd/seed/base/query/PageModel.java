package com.jd.seed.base.query;

import java.util.List;

/**
 * <pre>
 * 分页对象
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年5月12日 下午8:20:51
 */
public class PageModel<T> {
	// 页长
	private long size = 20;
	// 总行数
	private long total = 0;
	// 当前页
	private long currPage = 1;
	// 数据列表
	private List<T> data;

	public PageModel() {
		
	}
	
	public PageModel(List<T> data) {
		
	}
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCurrPage() {
		return currPage;
	}

	public void setCurrPage(long currPage) {
		this.currPage = currPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * <pre>
	 * 获取总业数
	 * 
	 * </pre>
	 * 
	 * @return Integer
	 */
	public long getPageTotal() {
		long pageTotal = 0;
		if (total > 0 && size > 0) {
			pageTotal = total / size;
			if (total % size != 0) {
				pageTotal++;
			}
		}
		return pageTotal;
	}

	/**
	 * <pre>
	 * 下一页
	 * 
	 * </pre>
	 * 
	 * @return long
	 */
	public long next() {
		long nextPage = currPage + 1;
		if (nextPage > lastPage()) {
			return currPage;
		}
		return nextPage;
	}

	/**
	 * <pre>
	 * 上一页
	 * 
	 * </pre>
	 * 
	 * @param long
	 */
	public long pre() {
		long prePage = currPage - 1;
		if (prePage <= 0) {
			return currPage;
		}
		return prePage;
	}

	/**
	 * <pre>
	 * 第一页
	 * 
	 * </pre>
	 */
	public long firstPage() {

		return total > 0 ? 1 : 0;
	}

	/**
	 * <pre>
	 * 最后一页
	 * 
	 * </pre>
	 */
	public long lastPage() {
		long endPageNo = total / size;
		if (total / size != 0) {
			endPageNo++;
		}
		return endPageNo;
	}

	/**
	 * <pre>
	 * 判断是否为空页
	 * 
	 * 描述
	 * data为空,total为0
	 * </pre>
	 */
	public boolean isEmpty() {
		if (null == data || data.isEmpty() || total == 0) {
			return true;
		}
		return false;
	}
}
