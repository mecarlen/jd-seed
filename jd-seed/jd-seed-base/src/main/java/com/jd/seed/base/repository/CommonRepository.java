package com.jd.seed.base.repository;

import java.io.Serializable;
import java.util.List;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 通用仓储接口
 * 
 * 描述
 * 	1、定义仓储的通用方法
 * 
 * @param Id
 *            实体唯一标识类型，要求是实现Serializable接口的类型,推荐用String
 * @param E
 *            具体Entity类
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午4:53:22
 */
public interface CommonRepository<ID extends Serializable, E extends Entity<ID, ?>> {
	/**
	 * <pre>
	 * 创建
	 * 
	 * </pre>
	 * 
	 * @param e
	 *            E 实体实例
	 */
	void insert(E e);

	/**
	 * <pre>
	 * 更新
	 * 
	 * </pre>
	 * 
	 * @param e
	 *            E 实体实例
	 * @return boolean
	 */
	boolean update(E e);

	/**
	 * <pre>
	 * 删除
	 * 
	 * </pre>
	 * 
	 * @param e
	 *            E 实体实例
	 * @return boolean
	 */
	boolean delete(E e);

	/**
	 * <pre>
	 * 根据主键取
	 * 
	 * </pre>
	 * 
	 * @param id
	 *            Id 实体实例id
	 * @return T 实体实例
	 */
	E selectById(ID id);

	/**
	 * <pre>
	 * 取所有
	 * 
	 * </pre>
	 * 
	 * @return List<E> 实体实例列表
	 */
	List<E> selectAll();
}
