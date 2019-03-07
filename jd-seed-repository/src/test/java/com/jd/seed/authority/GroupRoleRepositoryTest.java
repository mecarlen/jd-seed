package com.jd.seed.authority;


import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.GroupEntity;
import com.jd.seed.authority.domain.GroupRoleEntity;
import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.query.GroupQuery;
import com.jd.seed.authority.query.GroupRoleQuery;
import com.jd.seed.authority.query.RoleQuery;
import com.jd.seed.authority.repository.GroupRoleRepository;

/**
 * <pre>
 * 用户组与角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 上午9:41:40
 */
public class GroupRoleRepositoryTest extends RepositoryBaseTest {
	@Resource
	private GroupRoleRepository groupRoleRepository;
	@Resource
	private GroupRoleQuery groupRoleQuery;
	@Resource
	private GroupQuery groupQuery;
	@Resource
	private RoleQuery roleQuery;

	@Test
	public void insert() {
		GroupEntity group = groupQuery.selectById(1L);
		RoleEntity role = roleQuery.selectById(1L);
		GroupRoleEntity entity = new GroupRoleEntity.Builder(group, role).build();
		groupRoleRepository.insert(entity);
		isTrue(entity.getId() > 0, "新增用户组与角色关系");
	}

	@Test
	public void select() {
		List<GroupRoleEntity> grlist = groupRoleQuery.selectAll();
		isTrue(CollectionUtils.isNotEmpty(grlist), "查询用户组与角色关系失败");
	}
}
