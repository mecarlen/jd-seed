package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Group;
import com.jd.seed.authority.GroupRole;
import com.jd.seed.authority.Role;
import com.jd.seed.base.domain.BaseVO;

/**
 * <pre>
 * 用户组 - 角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午3:58:13
 */
public class GroupRoleVO extends BaseVO<Long, GroupRoleEntity> implements GroupRole {
	// 用户组
	private GroupVO group;
	// 角色
	private RoleVO role;

	private GroupRoleVO() {
	}

	private GroupRoleVO(Builder builder) {
		this.id = builder.id;
		this.group = builder.group;
		this.role = builder.role;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private GroupVO group;
		final private RoleVO role;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(final Group group, final Role role) {
			this.group = GroupVO.getInstance(group);
			this.role = RoleVO.getInstance(role);
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

		public GroupRoleVO build() {
			return new GroupRoleVO(this);
		}
	}

	private void setGroup(GroupVO group) {
		this.group = group;
	}

	private void setRole(RoleVO role) {
		this.role = role;
	}

	@Override
	public GroupVO getGroup() {
		return group;
	}

	@Override
	public RoleVO getRole() {
		return role;
	}

	@Override
	public GroupRoleVO id(Long id) {
		this.setId(id);
		return this;
	}

	public GroupRoleVO group(Group group) {
		if (null != group) {
			this.setGroup(GroupVO.getInstance(group));
		}
		return this;
	}

	public GroupRoleVO role(Role role) {
		if (null != role) {
			this.setRole(RoleVO.getInstance(role));
		}
		return this;
	}

	@Override
	public GroupRoleVO state(State state) {
		if (null != state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public GroupRoleVO createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public GroupRoleVO updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static GroupRoleVO getInstance(GroupRole gr) {
		if (null == gr) {
			return null;
		}
		return new Builder(gr.getGroup(), gr.getRole()).id(gr.getId()).state(State.getInstance(gr.getState()))
				.createTime(gr.getCreateTime()).updateTime(gr.getUpdateTime()).build();
	}

	@Override
	public GroupRoleEntity toEntity() {
		return GroupRoleEntity.getInstance(this);
	}

}
