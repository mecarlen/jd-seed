package com.jd.seed.authority;

import javax.annotation.Resource;

import org.junit.Test;

import static org.springframework.util.Assert.isTrue;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.GroupEntity;
import com.jd.seed.authority.repository.GroupRepository;

/**
 * <pre>
 * 分组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 上午9:03:22
 */
public class GroupRepositoryTest extends RepositoryBaseTest {
	@Resource
	private GroupRepository groupRepository;
	
	@Test
	public void insert() {
		GroupEntity entity = new GroupEntity.Builder("正式员工组", "formal_empl_group").descr("正式员工组").build();
		groupRepository.insert(entity);
		isTrue(entity.getId()>0, "新增用户组失败");
	}
}
