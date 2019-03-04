package com.jd.seed.organization;
/**
 * <pre>
 * 员工测试类
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午5:23:30
 */

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.organization.domain.EmployeeEntity;
import com.jd.seed.organization.query.EmployeeQuery;
import com.jd.seed.organization.repository.EmployeeRepository;

public class EmployeeRepositoryTest extends RepositoryBaseTest {
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private EmployeeQuery employeeQuery;
	@Test
	public void insert() {
		EmployeeEntity entity = new EmployeeEntity.Builder("王一", "wangyi", "12345", "wangyi@jd.com", "13612345678")
				.descr("王一").unityCode("JD1001").build();
		employeeRepository.insert(entity);
		isTrue(entity.getId()!=null, "添加员工失败");
	}
	
	@Test
	public void select() {
		List<EmployeeEntity> entitylist = employeeQuery.selectAll();
		isTrue(CollectionUtils.isNotEmpty(entitylist),"员工查询失败");
	}
}
