package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.UserGroupEntity;
import com.jd.seed.authority.domain.UserGroupVO;
import com.jd.seed.authority.query.UserGroupQuery;
import com.jd.seed.authority.repository.UserGroupRepository;
import com.jd.seed.authority.service.UserGroupService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 用户 - 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午4:12:26
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl<Long, UserGroupEntity, UserGroupVO, UserGroupRepository>
		implements UserGroupService {
	@Resource
	public void setUserGroupRepository(UserGroupRepository userGroupRepository) {
		super.setRepository(userGroupRepository);
	}

	@Resource
	public void setUserGroupQuery(UserGroupQuery userGroupQuery) {
		super.setQuery(userGroupQuery);
	}
}
