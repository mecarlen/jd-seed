package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 用户-角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:39:36
 */
public interface UserRole extends Entity<Long>{
	User getUser();
	
	Role getRole();
}
