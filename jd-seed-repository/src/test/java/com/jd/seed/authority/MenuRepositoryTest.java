package com.jd.seed.authority;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.jd.seed.RepositoryBaseTest;
import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.authority.query.MenuQuery;
import com.jd.seed.authority.repository.MenuRepository;

/**
 * <pre>
 * 菜单仓储
 * 
 * </pre>
 * 
 * @author mecarlen 2018年8月6日 下午4:12:27
 */
public class MenuRepositoryTest extends RepositoryBaseTest {
	@Resource
	private MenuRepository menuRepository;
	@Resource
	private MenuQuery menuQuery;

	@Test
	public void insert() {
		MenuEntity menu = new MenuEntity.Builder("人事门户", "s_0001", "1", "http://ssc.jd.com", 0).build()
				.sequence(0).descr("人事门户系统");
		menuRepository.insert(menu);
		isTrue(menu.getId() > 0, "menu city failure");
	}

	@Test
	public void selectAll() {
		List<MenuEntity> mlist = menuQuery.selectAll();
//		isTrue(null != mlist, "select all failure");
		Assertions.assertThat(mlist).isNotEmpty().as("菜单查询失败");
	}
}
