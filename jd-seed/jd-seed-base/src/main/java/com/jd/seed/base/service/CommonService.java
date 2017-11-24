package com.jd.seed.base.service;

import java.io.Serializable;
import java.util.List;

import com.jd.seed.base.domain.ValueObject;

/**
 * <pre>
 * 通用service
 * 
 * 描述:
 * 1、基础核心业务逻辑服务
 * 2、为具体领域服务提供底层通用逻辑
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:13:42
 */
public interface CommonService<ID extends Serializable, V extends ValueObject<ID, ?>> {
	/**
	 * <pre>
	 * 创建
	 * 
	 * </pre>
	 * 
	 * @param v
	 *            V具体ValueObject类型,其必须继承ValueObject<?,?>
	 * @return ID ValueObject唯一标示
	 */
	ID create(V v);

	/**
	 * <pre>
	 * 更新
	 * 
	 * </pre>
	 * 
	 * @param v
	 *            V 具体ValueObject类型
	 * @return boolean
	 */
	boolean modify(V v);

	/**
	 * <pre>
	 * 删除
	 * 
	 * </pre>
	 * 
	 * @param v
	 *            V 具体ValueObject类型
	 * @return boolean
	 */
	boolean remove(V v);

	/**
	 * <pre>
	 * 根据主键取
	 * 
	 * </pre>
	 * 
	 * @param id
	 *            ID ValueObject唯一标示
	 * @return V 具体ValueObject
	 */
	V query(ID id);

	/**
	 * <pre>
	 * 取所有
	 * 
	 * </pre>
	 * 
	 * @return List<V>
	 */
	List<V> query();
}
