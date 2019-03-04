package com.jd.seed.authority;

import javax.annotation.Resource;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.jd.seed.ServiceBaseTest;
import com.jd.seed.authority.domain.RoleVO;
import com.jd.seed.authority.service.RoleService;
import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageParams;
import com.jd.seed.base.query.PageThreadLocal;

/**
 * <pre>
 * 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:34:19
 */
public class RoleServiceTest extends ServiceBaseTest {
	@Resource
	private RoleService roleService;

	@Test
	public void find() {
		PageParams<RoleVO> pp = new PageParams<>();
		RoleVO conditions = null;
		PageThreadLocal.setParams(pp);
		PageModel<RoleVO> page = roleService.findByPage(conditions);
		Assertions.assertThat(page.getTotal()).isGreaterThanOrEqualTo(1).describedAs("角色查询失败");
	}
}
