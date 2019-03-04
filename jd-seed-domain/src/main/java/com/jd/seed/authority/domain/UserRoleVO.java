package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Role;
import com.jd.seed.authority.User;
import com.jd.seed.authority.UserRole;
import com.jd.seed.base.domain.BaseVO;
import com.jd.seed.organization.Employee;
import com.jd.seed.organization.domain.EmployeeVO;

/**
 * <pre>
 * 用户 - 角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:04:51
 */
public class UserRoleVO extends BaseVO<Long, UserRoleEntity> implements UserRole {
	// 用户
	private User user;
	// 角色
	private RoleVO role;

	private UserRoleVO() {
	}

	private UserRoleVO(Builder builder) {
		this.id = builder.id;
		this.user = builder.user;
		this.role = builder.role;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private User user;
		final private RoleVO role;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(User user, Role role) {
			if (user instanceof Employee) {
				this.user = EmployeeVO.getInstance((Employee) user);
			} else {
				this.user = user;
			}
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

		public UserRoleVO build() {
			return new UserRoleVO(this);
		}
	}

	private void setUser(User user) {
		this.user = user;
	}

	private void setRole(RoleVO role) {
		this.role = role;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public RoleVO getRole() {
		return role;
	}

	@Override
	public UserRoleVO id(Long id) {
		this.setId(id);
		return null;
	}

	public UserRoleVO user(User user) {
		if (null != user) {
			if (user instanceof Employee) {
				this.setUser(EmployeeVO.getInstance((Employee) user));
			} else {
				this.setUser(user);
			}
		}
		return this;
	}

	public UserRoleVO role(Role role) {
		if (null != role) {
			this.setRole(RoleVO.getInstance(role));
		}
		return this;
	}

	@Override
	public UserRoleVO state(State state) {
		if (null != state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public UserRoleVO createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public UserRoleVO updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static UserRoleVO getInstance(UserRole ur) {
		if (null == ur) {
			return null;
		}

		return new Builder(ur.getUser(), ur.getRole()).id(ur.getId()).state(State.getInstance(ur.getState()))
				.createTime(ur.getCreateTime()).updateTime(ur.getUpdateTime()).build();
	}

	@Override
	public UserRoleEntity toEntity() {

		return UserRoleEntity.getInstance(this);
	}

}
