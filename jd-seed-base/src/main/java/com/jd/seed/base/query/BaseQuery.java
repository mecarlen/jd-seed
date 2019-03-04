package com.jd.seed.base.query;

import java.io.Serializable;
import java.util.List;

import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BaseVO;

/**
 * <pre>
 * 通用查询
 * 
 * </pre>
 * 
 * @author mecarlen 2018年9月6日 下午4:03:00
 */
public interface BaseQuery<ID extends Serializable, E extends BaseEntity<ID, ?>, V extends BaseVO<ID,E>> {
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
	
	List<E> select(V conditions);
}
