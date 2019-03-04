package com.jd.seed.authority;

import static org.springframework.util.Assert.isTrue;

import javax.annotation.Resource;

import org.junit.Test;

import com.jd.seed.ServiceBaseTest;
import com.jd.seed.authority.domain.MenuVO;
import com.jd.seed.authority.service.MenuService;
import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageParams;
import com.jd.seed.base.query.PageThreadLocal;

/**
 * <pre>
 * 菜单
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月5日 下午2:35:12
 */
public class MenuServiceTest extends ServiceBaseTest{
	@Resource
	private MenuService menuService;

	@Test
	public void queryPage() {
		PageParams<MenuVO> pp = new PageParams<MenuVO>();
		MenuVO conditions = null;
		PageThreadLocal.setParams(pp);
		PageModel<MenuVO> page = menuService.findByPage(conditions);
		isTrue(page.getTotal() > 0, "query menu by page failure");
	}
}
