package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Group;
import com.jd.seed.authority.GroupRole;
import com.jd.seed.authority.Role;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;

/**
 * <pre>
 * 用户组 - 角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午3:57:38
 */
public class GroupRoleEntity extends BaseEntity<Long, GroupRoleVO> implements GroupRole {
	// 用户组
	private GroupEntity group;
	// 角色
	private RoleEntity role;

	protected GroupRoleEntity() {
	}

	private GroupRoleEntity(Builder builder) {
		this.id = builder.id;
		this.group = builder.group;
		this.role = builder.role;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private GroupEntity group;
		final private RoleEntity role;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(final Group group, final Role role) {
			if (null == group || null == role) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			this.group = GroupEntity.getInstance(group);
			this.role = RoleEntity.getInstance(role);
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder state(State state) {
			if (null != state) {
				this.state = state;
			}
			return this;
		}

		public Builder createTime(Date createTime) {
			if (null != createTime) {
				this.createTime = new Date(createTime.getTime());
			}
			return this;
		}

		public Builder updateTime(Date updateTime) {
			if (null != updateTime) {
				this.updateTime = new Date(updateTime.getTime());
			}
			return this;
		}

		public GroupRoleEntity build() {
			return new GroupRoleEntity(this);
		}
	}

	private void setGroup(GroupEntity group) {
		this.group = group;
	}

	private void setRole(RoleEntity role) {
		this.role = role;
	}

	@Override
	public GroupEntity getGroup() {
		return group;
	}

	@Override
	public RoleEntity getRole() {
		return role;
	}

	@Override
	public GroupRoleEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public GroupRoleEntity group(Group group) {
		if (null != group) {
			this.setGroup(GroupEntity.getInstance(group));
		}
		return this;
	}

	public GroupRoleEntity role(Role role) {
		if (null != role) {
			this.setRole(RoleEntity.getInstance(role));
		}
		return this;
	}

	@Override
	public GroupRoleEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public GroupRoleEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public GroupRoleEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static GroupRoleEntity getInstance(GroupRole gr) {
		if (null == gr) {
			return null;
		}
		return new Builder(gr.getGroup(), gr.getRole()).id(gr.getId()).state(State.getInstance(gr.getState()))
				.createTime(gr.getCreateTime()).updateTime(gr.getUpdateTime()).build();
	}

	@Override
	public GroupRoleVO toVO() {
		return GroupRoleVO.getInstance(this);
	}

}
