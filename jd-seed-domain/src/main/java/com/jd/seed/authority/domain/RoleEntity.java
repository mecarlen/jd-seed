package com.jd.seed.authority.domain;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.jd.seed.authority.Role;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;

/**
 * <pre>
 * 解色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午10:13:17
 */
public class RoleEntity extends BaseEntity<Long, RoleVO> implements Role {
	// 名称
	private String name;
	// 统一编码
	private String unityCode;
	// 类型
	private Integer type;
	// 描述
	private String descr;

	private RoleEntity() {
	}

	private RoleEntity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.unityCode = builder.unityCode;
		this.type = builder.type.code();
		this.descr = builder.descr;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		// id
		private Long id;
		// 名称
		final private String name;
		// 统一编码
		final private String unityCode;
		// 类型
		private Type type = Type.SYSTEM;
		// 描述
		private String descr;
		// 状态
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(final String name, final String unityCode) {
			if (StringUtils.isAnyEmpty(name, unityCode)) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			this.name = name;
			this.unityCode = unityCode;
		}

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder descr(final String descr) {
			this.descr = descr;
			return this;
		}

		public Builder type(final Type type) {
			this.type = type;
			return this;
		}

		public Builder state(final State state) {
			this.state = state;
			return this;
		}

		public Builder createTime(final Date createTime) {
			if (null != createTime) {
				this.createTime = new Date(createTime.getTime());
			}
			return this;
		}

		public Builder updateTime(final Date updateTime) {
			if (null != updateTime) {
				this.updateTime = new Date(updateTime.getTime());
			}
			return this;
		}

		public RoleEntity build() {
			return new RoleEntity(this);
		}
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setType(Integer type) {
		this.type = type;
	}

	private void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUnityCode() {
		return unityCode;
	}

	@Override
	public Integer getType() {
		return type;
	}

	@Override
	public String getDescr() {
		return descr;
	}

	@Override
	public RoleEntity id(Long id) {
		setId(id);
		return this;
	}

	@Override
	public RoleEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public RoleEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public RoleEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public RoleEntity name(String name) {
		if (StringUtils.isNotBlank(name)) {
			this.setName(name);
		}
		return this;
	}

	public RoleEntity unityCode(String untiyCode) {
		if (StringUtils.isNotBlank(name)) {
			this.setUnityCode(untiyCode);
		}
		return this;
	}

	public RoleEntity type(int type) {
		this.setType(type);
		return this;
	}

	public RoleEntity descr() {
		this.setDescr(descr);
		return this;
	}

	public static RoleEntity getInstance(final Role role) {
		if (null == role) {
			return null;
		}
		return new RoleEntity.Builder(role.getName(), role.getUnityCode()).id(role.getId()).descr(role.getDescr())
				.type(Type.getInstance(role.getType())).state(State.getInstance(role.getState()))
				.createTime(role.getCreateTime()).updateTime(role.getUpdateTime()).build();
	}

	@Override
	public RoleVO toVO() {
		return RoleVO.getInstance(this);
	}
}
