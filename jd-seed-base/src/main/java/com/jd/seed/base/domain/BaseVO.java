package com.jd.seed.base.domain;
/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月12日 下午3:06:18
 */

import java.io.Serializable;
import java.util.Date;

public abstract class BaseVO<ID extends Serializable, E extends BaseEntity<ID, ?>> implements Entity<ID> {
	// id
	protected ID id;
	// 状态
	protected State state;
	// 创建时间
	protected Date createTime;
	// 更新时间
	protected Date updateTime;

	// setter
	protected void setId(ID id) {
		this.id = id;
	}

	protected void setState(State state) {
		this.state = state;
	}

	protected void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	protected void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	// getter
	@Override
	public ID getId() {
		return id;
	}

	@Override
	public Integer getState() {
		return state.code();
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * <pre>
	 * 给VO唯一标示赋值方法,在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @param id
	 *            ID VO唯一标示类型
	 * @return BaseVO<ID, E>
	 */
	public abstract BaseVO<ID, E> id(ID id);

	/**
	 * <pre>
	 * 给VO设置状态
	 * 
	 * </pre>
	 * 
	 * @param state
	 *            EnumState
	 * @return BaseVO<ID, E>
	 */
	public abstract BaseVO<ID, E> state(final State state);

	/**
	 * <pre>
	 * 给VO创建时间赋值方法，在CommonServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @param createTime
	 *            Date 创建时间
	 * @return BaseVO<ID, E>
	 */
	public abstract BaseVO<ID, E> createTime(Date createTime);

	/**
	 * <pre>
	 * 给VO更新时间赋值方法，在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @param updateTime
	 *            Date 更新时间
	 * @return BaseVO<ID, E>
	 */
	public abstract BaseVO<ID, E> updateTime(Date updateTime);

	/**
	 * <pre>
	 * VO转换成Entity方法,在BaseServiceImpl用到,此方法其子类必须实现
	 * 
	 * </pre>
	 * 
	 * @return E 具体Entity类型
	 */
	public abstract E toEntity();
}
