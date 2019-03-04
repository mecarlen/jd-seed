package com.jd.seed.authority;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 用户-用户组关系
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 下午4:39:53
 */
public interface UserGroup extends Entity<Long>{
	User getUser();
	
	Group getGroup();
}
