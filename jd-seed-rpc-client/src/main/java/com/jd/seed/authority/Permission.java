package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 授权
 * 
 * 描述角色与资源关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午1:56:58
 */
public interface Permission extends Entity<Long> {
	
	Role getRole();
	
	/**
	 * <pre>
	 * 资源包括菜单和数据等
	 * 
	 * </pre>
	 * 
	 * @return T
	 */
	<T extends Resource> T getResource();


	/**
	 * <pre>
	 * 权限是否可下放
	 * 
	 * </pre>
	 * 
	 * @return boolean
	 */
	boolean isWithOpt();

	int getOperation();
}
