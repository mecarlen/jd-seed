package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.GroupEntity;
import com.jd.seed.authority.domain.GroupVO;
import com.jd.seed.authority.query.GroupQuery;
import com.jd.seed.authority.repository.GroupRepository;
import com.jd.seed.authority.service.GroupService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:19:01
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Long, GroupEntity, GroupVO, GroupRepository>
		implements GroupService {
	
	@Resource
	public void setGroupRepository(GroupRepository groupRepository) {
		super.setRepository(groupRepository);
	}
	
	@Resource
	public void setGroupQuery(GroupQuery groupQuery) {
		super.setQuery(groupQuery);
	}
}
