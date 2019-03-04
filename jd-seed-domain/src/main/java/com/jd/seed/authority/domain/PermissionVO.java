package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Data;
import com.jd.seed.authority.Menu;
import com.jd.seed.authority.Operation;
import com.jd.seed.authority.Permission;
import com.jd.seed.authority.Resource;
import com.jd.seed.authority.Role;
import com.jd.seed.base.domain.BaseVO;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:31:58
 */
public class PermissionVO extends BaseVO<Long, PermissionEntity> implements Permission {
	// 角色
	private RoleVO role;
	// 资源
	private Resource resource;
	// 操作
	private Operation.Code operation;
	// 权限是否可下放
	private boolean withOpt;

	private PermissionVO() {
	}

	private PermissionVO(Builder builder) {
		this.id = builder.id;
		this.role = builder.role;
		this.resource = builder.resource;
		this.operation = builder.operation;
		this.withOpt = builder.withOpt;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private RoleVO role;
		final private Resource resource;
		private Operation.Code operation = Operation.Code.READ;
		private boolean withOpt = false;
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(Role role, Resource resource) {
			this.role = RoleVO.getInstance(role);
			if (resource instanceof Menu) {
				this.resource = MenuVO.getInstance((Menu) resource);
			} else {
				this.resource = resource;
			}
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder operation(Operation.Code operation) {
			if (null != operation) {
				this.operation = operation;
			}
			return this;
		}

		public Builder withOpt(boolean withOpt) {
			this.withOpt = withOpt;
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

		public PermissionVO build() {
			return new PermissionVO(this);
		}
	}

	private void setRole(RoleVO role) {
		this.role = role;
	}

	private void setResource(Resource resource) {
		this.resource = resource;
	}

	private void setOperation(Operation.Code operation) {
		this.operation = operation;
	}

	private void setWithOpt(boolean with) {
		this.withOpt = with;
	}

	@Override
	public RoleVO getRole() {
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource getResource() {
		return resource;
	}

	@Override
	public int getOperation() {
		return operation.code();
	}

	@Override
	public boolean isWithOpt() {
		return withOpt;
	}

	@Override
	public PermissionVO id(Long id) {
		this.setId(id);
		return this;
	}
	public PermissionVO role(Role role) {
		if (null != role) {
			this.setRole(RoleVO.getInstance(role));
		}
		return this;
	}

	public PermissionVO resource(Resource resource) {
		if (resource instanceof Menu) {
			// 菜单
			this.setResource(MenuVO.getInstance((Menu) resource));
		} else if (resource instanceof Data) {
			// 数据

		}
		return this;
	}

	public PermissionVO operation(Operation.Code operation) {
		if (null != operation) {
			this.setOperation(operation);
		}
		return this;
	}

	public PermissionVO withOpt(boolean withOpt) {
		this.setWithOpt(withOpt);
		return this;
	}

	@Override
	public PermissionVO state(State state) {
		if(null!=state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public PermissionVO createTime(Date createTime) {
		if(null!=createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public PermissionVO updateTime(Date updateTime) {
		if(null!=updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static PermissionVO getInstance(Permission permission) {
		if (null == permission) {
			return null;
		}
		return new PermissionVO.Builder(permission.getRole(), permission.getResource()).id(permission.getId())
				.operation(Operation.Code.getInstance(permission.getOperation())).withOpt(permission.isWithOpt())
				.state(State.getInstance(permission.getState())).createTime(permission.getCreateTime())
				.updateTime(permission.getUpdateTime()).build();
	}

	@Override
	public PermissionEntity toEntity() {
		return PermissionEntity.getInstance(this);
	}

}
