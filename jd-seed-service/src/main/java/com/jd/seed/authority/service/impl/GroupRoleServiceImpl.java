package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.GroupRoleEntity;
import com.jd.seed.authority.domain.GroupRoleVO;
import com.jd.seed.authority.query.GroupRoleQuery;
import com.jd.seed.authority.repository.GroupRoleRepository;
import com.jd.seed.authority.service.GroupRoleService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 用户组 - 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午4:08:29
 */
@Service("groupRoleService")
public class GroupRoleServiceImpl extends BaseServiceImpl<Long, GroupRoleEntity, GroupRoleVO, GroupRoleRepository>
		implements GroupRoleService {

	@Resource
	public void setGroupRoleRepository(GroupRoleRepository groupRoleRepository) {
		super.setRepository(groupRoleRepository);
	}

	@Resource
	public void setGroupRoleQuery(GroupRoleQuery groupRoleQuery) {
		super.setQuery(groupRoleQuery);
	}

}
