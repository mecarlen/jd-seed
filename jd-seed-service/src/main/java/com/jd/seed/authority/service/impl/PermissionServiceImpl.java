package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.PermissionEntity;
import com.jd.seed.authority.domain.PermissionVO;
import com.jd.seed.authority.query.PermissionQuery;
import com.jd.seed.authority.repository.PermissionRepository;
import com.jd.seed.authority.service.PermissionService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:28:37
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Long, PermissionEntity, PermissionVO, PermissionRepository>
		implements PermissionService {
	@Resource
	public void setPermissionRepository(PermissionRepository permissionRepository) {
		super.setRepository(permissionRepository);
	}

	@Resource
	public void setPermissionQuery(PermissionQuery permissionQuery) {
		super.setQuery(permissionQuery);
	}
}
