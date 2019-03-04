package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import com.jd.seed.authority.domain.UserRoleEntity;
import com.jd.seed.authority.domain.UserRoleVO;
import com.jd.seed.authority.query.UserRoleQuery;
import com.jd.seed.authority.repository.UserRoleRepository;
import com.jd.seed.authority.service.UserRoleService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 用户 - 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午4:15:49
 */
public class UserRoleServiceImpl extends BaseServiceImpl<Long, UserRoleEntity, UserRoleVO, UserRoleRepository>
		implements UserRoleService {

	@Resource
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		super.setRepository(userRoleRepository);
	}

	@Resource
	public void setUserRoleQuery(UserRoleQuery userRoleQuery) {
		super.setQuery(userRoleQuery);
	}
}
