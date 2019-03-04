package com.jd.seed.authority;

import static org.springframework.util.Assert.isTrue;

import javax.annotation.Resource;

import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.repository.RoleRepository;

/**
 * <pre>
 * 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午6:03:43
 */
public class RoleRepositoryTest extends RepositoryBaseTest {

	@Resource
	private RoleRepository roleRepository;

	@Test
	public void insert() {
		RoleEntity entity = new RoleEntity.Builder("系统管理员", "sys_admin").type(Role.Type.SYSTEM).descr("系统管理员").build();
		roleRepository.insert(entity);
		isTrue(entity.getId()>0, "添加角色失败");
	}
}
