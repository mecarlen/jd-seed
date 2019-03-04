package com.jd.seed.authority;

import javax.annotation.Resource;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.jd.seed.ServiceBaseTest;
import com.jd.seed.authority.domain.GroupVO;
import com.jd.seed.authority.service.GroupService;
import com.jd.seed.base.domain.Entity.State;
import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageParams;
import com.jd.seed.base.query.PageThreadLocal;

/**
 * <pre>
 * 用户分组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午3:48:01
 */
public class GroupServiceTest extends ServiceBaseTest {
	@Resource
	private GroupService groupService;
	
	@Test
	public void find() {
		PageParams<GroupVO> pp =new PageParams<>();
		pp.setPageSize(50);
		GroupVO conditions = new GroupVO.Builder("", "").state(State.NORMAL).build();
		PageThreadLocal.setParams(pp);
		PageModel<GroupVO> page = groupService.findByPage(conditions);
//		Assertions.assertThat(page.getSize()).isEqualTo(50L).describedAs("页长不正确");
		Assertions.assertThat(page.getTotal()).isGreaterThanOrEqualTo(1).describedAs("查询结果总条数不正确");
	}
}
