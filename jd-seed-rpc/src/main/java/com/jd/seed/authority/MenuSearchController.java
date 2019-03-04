package com.jd.seed.authority;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jd.seed.authority.domain.MenuVO;
import com.jd.seed.authority.service.MenuService;
import com.jd.seed.base.query.PageModel;

/**
 * <pre>
 * 菜单检索
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午4:27:54
 */
@RestController
@RequestMapping("/authority/menu/search")
public class MenuSearchController {
	@Resource
	private MenuService menuService;

	@PostMapping(value = "/page/{pageSize}/{pageNo}", produces = "application/json", consumes = "application/json")
	public PageModel<MenuVO> page(@PathVariable(name = "pageSize") Integer pageSize,
			@PathVariable(name = "pageNo") Integer pageNo) {
		MenuVO conditions = null;
		return menuService.findByPage(conditions);
	}
}
