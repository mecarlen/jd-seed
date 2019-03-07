package com.jd.seed.authority;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.authority.domain.PermissionEntity;
import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.query.MenuQuery;
import com.jd.seed.authority.query.PermissionQuery;
import com.jd.seed.authority.query.RoleQuery;
import com.jd.seed.authority.repository.PermissionRepository;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午6:01:06
 */
public class PermissionRepositoryTest extends RepositoryBaseTest {

	@Resource
	private PermissionRepository permissionRepository;
	@Resource
	private PermissionQuery permissionQuery;
	@Resource
	private RoleQuery roleQuery;
	@Resource
	private MenuQuery menuQuery;

	@Test
	public void insert() {
		RoleEntity role = roleQuery.selectById(1L);
		MenuEntity resource = menuQuery.selectById(1L);
		PermissionEntity entity = new PermissionEntity.Builder(role, resource).build();
		permissionRepository.insert(entity);

		isTrue(entity.getId() > 0, "添加授权失败");
	}

	@Test
	public void select() {
		PermissionEntity entity = permissionQuery.selectById(1L);
		isTrue(null != entity, "查询授权失败");
	}
	
	@Test
	public void selectAll() {
		List<PermissionEntity> entitylist = permissionQuery.selectAll();
		isTrue(CollectionUtils.isNotEmpty(entitylist),"查询授权失败");
	}
}
