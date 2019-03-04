package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 用户组 - 角色关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:39:10
 */
public interface GroupRole extends Entity<Long>{
	Group getGroup();

	Role getRole();
}
