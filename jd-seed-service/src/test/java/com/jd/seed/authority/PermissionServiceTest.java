package com.jd.seed.authority;

import javax.annotation.Resource;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.jd.seed.ServiceBaseTest;
import com.jd.seed.authority.domain.PermissionVO;
import com.jd.seed.authority.service.PermissionService;
import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageParams;
import com.jd.seed.base.query.PageThreadLocal;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:55:49
 */
public class PermissionServiceTest extends ServiceBaseTest {
	@Resource
	private PermissionService permissionService;

	@Test
	public void find() {
		PageParams<PermissionVO> pp = new PageParams<>();
		PermissionVO conditions = null;
		PageThreadLocal.setParams(pp);
		PageModel<PermissionVO> page = permissionService.findByPage(conditions);
		Assertions.assertThat(page.getTotal()).isGreaterThanOrEqualTo(1L).describedAs("授权查询失败");
	}
}
