package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Data;
import com.jd.seed.authority.Menu;
import com.jd.seed.authority.Operation;
import com.jd.seed.authority.Permission;
import com.jd.seed.authority.Resource;
import com.jd.seed.authority.Role;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:31:04
 */
public class PermissionEntity extends BaseEntity<Long, PermissionVO> implements Permission {
	// 角色
	private RoleEntity role;
	// 资源
	private Resource resource;
	// 资源类型
	private int resType;
	// 操作
	private int operation;
	// 权限是否可下放
	private boolean withOpt;

	protected PermissionEntity() {
	}

	private PermissionEntity(Builder builder) {
		this.id = builder.id;
		this.role = builder.role;
		this.setResource(builder.resource);
		this.operation = builder.operation.code();
		this.withOpt = builder.withOpt;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private Long id;
		final private RoleEntity role;
		final private Resource resource;
		private Operation.Code operation = Operation.Code.READ;
		private boolean withOpt = false;
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(Role role, Resource resource) {
			if (null == role || null == resource) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			this.role = RoleEntity.getInstance(role);
			if (resource instanceof Menu) {
				this.resource = MenuEntity.getInstance((Menu) resource);
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

		public PermissionEntity build() {
			return new PermissionEntity(this);
		}
	}

	private void setRole(RoleEntity role) {
		this.role = role;
	}

	private void setResource(Resource resource) {
		this.resource = resource;
		if (resource instanceof Data) {
			this.setResType(Resource.DATA_TYPE_CODE);
		} else {
			this.setResType(Resource.MENU_TYPE_CODE);
		}
	}

	private void setResType(int resType) {
		this.resType = resType;
	}

	private void setOperation(int operation) {
		this.operation = operation;
	}

	private void setWithOpt(boolean with) {
		this.withOpt = with;
	}

	@Override
	public RoleEntity getRole() {
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource getResource() {
		return resource;
	}

	public int getResType() {
		return resType;
	}

	@Override
	public int getOperation() {
		return operation;
	}

	@Override
	public boolean isWithOpt() {
		return withOpt;
	}

	@Override
	public PermissionEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public PermissionEntity role(Role role) {
		if (null != role) {
			this.setRole(RoleEntity.getInstance(role));
		}
		return this;
	}

	public PermissionEntity resource(Resource resource) {
		if (resource instanceof Menu) {
			// 菜单
			this.setResource(MenuEntity.getInstance((Menu) resource));
		} else if (resource instanceof Data) {
			// 数据

		}
		return this;
	}

	public PermissionEntity operation(Operation.Code operation) {
		if (null != operation) {
			this.setOperation(operation.code());
		}
		return this;
	}

	public PermissionEntity with(boolean with) {
		this.setWithOpt(with);
		return this;
	}

	@Override
	public PermissionEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public PermissionEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public PermissionEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	public static PermissionEntity getInstance(Permission permission) {
		if (null == permission) {
			return null;
		}
		return new PermissionEntity.Builder(permission.getRole(), permission.getResource()).id(permission.getId())
				.operation(Operation.Code.getInstance(permission.getOperation())).withOpt(permission.isWithOpt())
				.state(State.getInstance(permission.getState())).createTime(permission.getCreateTime())
				.updateTime(permission.getUpdateTime()).build();
	}

	@Override
	public PermissionVO toVO() {

		return PermissionVO.getInstance(this);
	}

}
