package com.jd.seed.authority.domain;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.jd.seed.authority.Group;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;

/**
 * <pre>
 * 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:32:22
 */
public class GroupEntity extends BaseEntity<Long, GroupVO> implements Group {
	// 统一标识
	private String unityCode;
	// 名称
	private String name;
	// 描述
	private String descr;

	private GroupEntity() {
	}

	private GroupEntity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.unityCode = builder.unityCode;
		this.descr = builder.descr;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private String name;
		final private String unityCode;
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

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder descr(String descr) {
			this.descr = descr;
			return this;
		}

		public Builder state(State state) {
			if (null != state) {
				this.state = state;
			}
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

		public GroupEntity build() {
			return new GroupEntity(this);
		}
	}

	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public String getUnityCode() {
		return unityCode;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescr() {
		return descr;
	}

	@Override
	public GroupEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public GroupEntity name(String name) {
		if (StringUtils.isNotBlank(name)) {
			this.setName(name);
		}
		return this;
	}

	public GroupEntity unityCode(String unityCode) {
		if (StringUtils.isNotBlank(unityCode)) {
			this.setUnityCode(unityCode);
		}
		return this;
	}

	public GroupEntity descr(String descr) {
		if (StringUtils.isNotBlank(descr)) {
			this.setDescr(descr);
		}
		return this;
	}

	@Override
	public GroupEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public GroupEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public GroupEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static GroupEntity getInstance(Group group) {
		if (null == group) {
			return null;
		}
		return new GroupEntity.Builder(group.getName(), group.getUnityCode()).id(group.getId()).descr(group.getDescr())
				.state(State.getInstance(group.getState())).createTime(group.getCreateTime())
				.updateTime(group.getUpdateTime()).build();
	}

	@Override
	public GroupVO toVO() {
		return GroupVO.getInstance(this);
	}

}
