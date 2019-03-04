package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Group;
import com.jd.seed.authority.User;
import com.jd.seed.authority.UserGroup;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;
import com.jd.seed.organization.Employee;
import com.jd.seed.organization.domain.EmployeeEntity;

/**
 * <pre>
 * 用户 - 用户组关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:05:23
 */
public class UserGroupEntity extends BaseEntity<Long, UserGroupVO> implements UserGroup {
	// 用户
	private User user;
	private int userType;
	// 用户组
	private GroupEntity group;

	protected UserGroupEntity() {
	}

	private UserGroupEntity(Builder builder) {
		this.id = builder.id;
		this.setUser(builder.user);
		this.group = builder.group;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private User user;
		final private GroupEntity group;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(User user, Group group) {
			if (null == user || null == group) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			if (user instanceof Employee) {
				this.user = EmployeeEntity.getInstance((Employee) user);
			} else {
				this.user = user;
			}
			this.group = GroupEntity.getInstance(group);
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

		public UserGroupEntity build() {
			return new UserGroupEntity(this);
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

	private void setGroup(GroupEntity group) {
		this.group = group;
	}

	@Override
	public User getUser() {
		return user;
	}

	public int getUserType() {
		return userType;
	}

	@Override
	public GroupEntity getGroup() {
		return group;
	}

	@Override
	public UserGroupEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public UserGroupEntity user(User user) {
		if (null != user) {
			if (user instanceof Employee) {
				this.setUser(EmployeeEntity.getInstance((Employee) user));
			} else {
				this.setUser(user);
			}
		}
		return this;
	}

	public UserGroupEntity group(Group group) {
		if (null != group) {
			this.setGroup(GroupEntity.getInstance(group));
		}
		return this;
	}

	@Override
	public UserGroupEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public UserGroupEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public UserGroupEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static UserGroupEntity getInstance(UserGroup ug) {
		if (null == ug) {
			return null;
		}

		return new Builder(ug.getUser(), ug.getGroup()).id(ug.getId()).state(State.getInstance(ug.getState()))
				.createTime(ug.getCreateTime()).updateTime(ug.getUpdateTime()).build();
	}

	@Override
	public UserGroupVO toVO() {

		return UserGroupVO.getInstance(this);
	}

}
