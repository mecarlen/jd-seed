package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.domain.RoleVO;
import com.jd.seed.authority.query.RoleQuery;
import com.jd.seed.authority.repository.RoleRepository;
import com.jd.seed.authority.service.RoleService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:23:34
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Long, RoleEntity, RoleVO, RoleRepository> implements RoleService {

	@Resource
	public void setRoleRepository(RoleRepository roleRepository) {
		super.setRepository(roleRepository);
	}
	
	@Resource
	public void setRoleQuery(RoleQuery roleQuery) {
		super.setQuery(roleQuery);
	}
}
