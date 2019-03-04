package com.jd.seed.authority;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jd.seed.authority.service.MenuService;

/**
 * <pre>
 * 菜单展示
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午4:24:13
 */
@Controller
@RequestMapping("/authority/menu/view")
public class MenuViewController {
	@Resource
	private MenuService menuService;
	
	@GetMapping("/list")
	public String list(ModelAndView mv) {
		
		mv.addObject("menulist", null);
		return "/authority/menu.html";
	}
}
