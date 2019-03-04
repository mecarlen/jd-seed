package com.jd.seed.authority.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.authority.domain.MenuVO;
import com.jd.seed.authority.query.MenuQuery;
import com.jd.seed.authority.repository.MenuRepository;
import com.jd.seed.authority.service.MenuService;
import com.jd.seed.base.service.impl.BaseServiceImpl;

/**
 * <pre>
 * 菜单
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月5日 下午2:22:11
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Long, MenuEntity, MenuVO, MenuRepository>
		implements MenuService {
	// log
	protected static Logger MENU_SERVICE_LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Resource
	public void setMenuRepository(MenuRepository menuRepository) {
		super.setRepository(menuRepository);
	}

	@Resource
	public void setMenuQuery(MenuQuery menuQuery) {
		super.setQuery(menuQuery);
	}
}
