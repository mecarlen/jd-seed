package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Group;
import com.jd.seed.authority.User;
import com.jd.seed.authority.UserGroup;
import com.jd.seed.base.domain.BaseVO;
import com.jd.seed.organization.Employee;
import com.jd.seed.organization.domain.EmployeeVO;

/**
 * <pre>
 * 用户 - 用户组关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:05:48
 */
public class UserGroupVO extends BaseVO<Long, UserGroupEntity> implements UserGroup {
	// 用户
	private User user;
	// 用户组
	private GroupVO group;

	private UserGroupVO() {
	}

	private UserGroupVO(Builder builder) {
		this.id = builder.id;
		this.user = builder.user;
		this.group = builder.group;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private User user;
		final private GroupVO group;
		private State state = State.NORMAL;
		private Date createTime;
		private Date updateTime;

		public Builder(User user, Group group) {
			if (user instanceof Employee) {
				this.user = EmployeeVO.getInstance((Employee) user);
			} else {
				this.user = user;
			}
			this.group = GroupVO.getInstance(group);
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

		public UserGroupVO build() {
			return new UserGroupVO(this);
		}
	}

	private void setUser(User user) {
		this.user = user;
	}

	private void setGroup(GroupVO group) {
		this.group = group;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public GroupVO getGroup() {
		return group;
	}

	@Override
	public UserGroupVO id(Long id) {
		this.setId(id);
		return this;
	}

	public UserGroupVO user(User user) {
		if (null != user) {
			if (user instanceof Employee) {
				this.setUser(EmployeeVO.getInstance((Employee) user));
			} else {
				this.setUser(user);
			}
		}
		return this;
	}

	public UserGroupVO group(Group group) {
		if (null != group) {
			this.setGroup(GroupVO.getInstance(group));
		}
		return this;
	}

	@Override
	public UserGroupVO state(State state) {
		if (null != state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public UserGroupVO createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public UserGroupVO updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static UserGroupVO getInstance(UserGroup ug) {
		if (null == ug) {
			return null;
		}
		return new Builder(ug.getUser(), ug.getGroup()).id(ug.getId()).state(State.getInstance(ug.getState()))
				.createTime(ug.getCreateTime()).updateTime(ug.getUpdateTime()).build();
	}

	@Override
	public UserGroupEntity toEntity() {

		return UserGroupEntity.getInstance(this);
	}

}
