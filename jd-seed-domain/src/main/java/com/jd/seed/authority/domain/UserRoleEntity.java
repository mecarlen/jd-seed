package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Role;
import com.jd.seed.authority.User;
import com.jd.seed.authority.UserRole;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;
import com.jd.seed.organization.Employee;
import com.jd.seed.organization.domain.EmployeeEntity;

/**
 * <pre>
 * 用户 - 角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:04:17
 */
public class UserRoleEntity extends BaseEntity<Long, UserRoleVO> implements UserRole {
	// 用户
	private User user;
	private int userType;
	// 角色
	private RoleEntity role;

	protected UserRoleEntity() {
	}

	private UserRoleEntity(Builder builder) {
		this.id = builder.id;
		this.setUser(builder.user);
		this.role = builder.role;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private User user;
		final private RoleEntity role;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(User user, Role role) {
			if (null == user || null == role) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			if (user instanceof Employee) {
				this.user = EmployeeEntity.getInstance((Employee) user);
			} else {
				this.user = user;
			}
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

		public UserRoleEntity build() {
			return new UserRoleEntity(this);
		}
	}

	private void setUser(User user) {
		this.user = user;
		if (user instanceof Employee) {
			this.setUserType(User.EMPLOYEE_TYPE_CODE);
		} else {
			this.setUserType(User.VISTOR_TYPE_CODE);
		}
	}

	private void setUserType(int userType) {
		this.userType = userType;
	}

	private void setRole(RoleEntity role) {
		this.role = role;
	}

	@Override
	public User getUser() {
		return user;
	}

	public int getUserType() {
		return userType;
	}

	@Override
	public RoleEntity getRole() {
		return role;
	}

	@Override
	public UserRoleEntity id(Long id) {
		this.setId(id);
		return null;
	}

	public UserRoleEntity user(User user) {
		if (null != user) {
			if (user instanceof Employee) {
				this.setUser(EmployeeEntity.getInstance((Employee) user));
			} else {
				this.setUser(user);
			}
		}
		return this;
	}

	public UserRoleEntity role(Role role) {
		if (null != role) {
			this.setRole(RoleEntity.getInstance(role));
		}
		return this;
	}

	@Override
	public UserRoleEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public UserRoleEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public UserRoleEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static UserRoleEntity getInstance(UserRole ur) {
		if (null == ur) {
			return null;
		}
		return new Builder(ur.getUser(), ur.getRole()).id(ur.getId()).state(State.getInstance(ur.getState()))
				.createTime(ur.getCreateTime()).updateTime(ur.getUpdateTime()).build();
	}

	@Override
	public UserRoleVO toVO() {

		return UserRoleVO.getInstance(this);
	}

}
