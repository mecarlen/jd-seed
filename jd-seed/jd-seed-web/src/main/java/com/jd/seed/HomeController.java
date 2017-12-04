package com.jd.seed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * 首页
 * </pre>
 *
 * @author mecarlen 2017年12月1日 下午1:47:22
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("header")
	public String header(){
		
		return "common/header";
	}
	
	@GetMapping("footer")
	public String footer(){
		
		return "common/footer";
	}
	
	@GetMapping("layout")
	public String layout(){
		
		return "common/layout";
	}
	
}
