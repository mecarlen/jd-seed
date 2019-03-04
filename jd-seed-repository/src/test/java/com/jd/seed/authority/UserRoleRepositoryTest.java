package com.jd.seed.authority;

import javax.annotation.Resource;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.domain.UserRoleEntity;
import com.jd.seed.authority.query.RoleQuery;
import com.jd.seed.authority.query.UserRoleQuery;
import com.jd.seed.authority.repository.UserRoleRepository;
import com.jd.seed.organization.query.EmployeeQuery;

/**
 * <pre>
 * 用户 - 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 上午10:50:00
 */
public class UserRoleRepositoryTest extends RepositoryBaseTest {
	@Resource
	private UserRoleRepository userRoleRepository;
	@Resource
	private UserRoleQuery userRoleQuery;
	@Resource
	private EmployeeQuery employeeQuery;
	@Resource
	private RoleQuery roleQuery;

	@Test
	public void insert() {
		RoleEntity role = roleQuery.selectById(2L);
		User user = employeeQuery.selectById(1L);
		UserRoleEntity entity = new UserRoleEntity.Builder(user, role).build();
		userRoleRepository.insert(entity);
		Assertions.assertThat(entity.getId()).isNotNull().as("添加用户-角色关系失败");
	}

}
