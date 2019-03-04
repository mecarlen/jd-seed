package com.jd.seed.authority.domain;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.jd.seed.authority.Group;
import com.jd.seed.base.domain.BaseVO;

/**
 * <pre>
 * 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:32:35
 */
public class GroupVO extends BaseVO<Long, GroupEntity> implements Group {
	// 统一标识
	private String unityCode;
	// 名称
	private String name;
	// 描述
	private String descr;

	private GroupVO() {
	}

	private GroupVO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.unityCode = builder.unityCode;
		this.descr = builder.descr;
		this.state = builder.state;
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

		public GroupVO build() {
			return new GroupVO(this);
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
	public GroupVO id(Long id) {
		this.setId(id);
		return this;
	}

	public GroupVO name(String name) {
		if (StringUtils.isNotBlank(name)) {
			this.setName(name);
		}
		return this;
	}

	public GroupVO unityCode(String unityCode) {
		if (StringUtils.isNotBlank(unityCode)) {
			this.setUnityCode(unityCode);
		}
		return this;
	}

	public GroupVO descr(String descr) {
		this.setDescr(descr);
		return this;
	}

	@Override
	public GroupVO state(State state) {
		if (null != state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public GroupVO createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public GroupVO updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static GroupVO getInstance(Group group) {
		if (null == group) {
			return null;
		}
		return new GroupVO.Builder(group.getName(), group.getUnityCode()).id(group.getId()).descr(group.getDescr())
				.state(State.getInstance(group.getState())).createTime(group.getCreateTime())
				.updateTime(group.getUpdateTime()).build();
	}

	@Override
	public GroupEntity toEntity() {
		return GroupEntity.getInstance(this);
	}

}
