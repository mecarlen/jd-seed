package com.jd.seed.authority;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.GroupEntity;
import com.jd.seed.authority.domain.UserGroupEntity;
import com.jd.seed.authority.query.GroupQuery;
import com.jd.seed.authority.query.UserGroupQuery;
import com.jd.seed.authority.repository.UserGroupRepository;
import com.jd.seed.organization.query.EmployeeQuery;

/**
 * <pre>
 * 用户 - 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 上午10:10:52
 */
public class UserGroupRepositoryTest extends RepositoryBaseTest {
	@Resource
	private UserGroupRepository userGroupRepository;
	@Resource
	private UserGroupQuery userGroupQuery;
	@Resource
	private GroupQuery groupQuery;
	@Resource
	private EmployeeQuery employeeQuery;

	@Test
	public void insert() {
		GroupEntity group = groupQuery.selectById(1L);
		User user = employeeQuery.selectById(1L);
		UserGroupEntity entity = new UserGroupEntity.Builder(user, group).build();
		userGroupRepository.insert(entity);
		Assert.isTrue(entity.getId() > 0, "用户-用户组关系新增失败");
	}

	@Test
	public void select() {
		List<UserGroupEntity> uglist = userGroupQuery.selectAll();

		Assert.isTrue(CollectionUtils.isNotEmpty(uglist), "用户-用户组关系查询失败");
	}
}
