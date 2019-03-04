package com.jd.seed;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageParams;
import com.jd.seed.base.query.PageThreadLocal;
import com.jd.seed.dictionary.domain.CityVO;
import com.jd.seed.dictionary.service.CityService;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午6:02:11
 */
public class CityServiceTest extends ServiceBaseTest {
	@Resource
	private CityService cityService;

	@Test
	public void query() {
		List<CityVO> citylist = cityService.find();
		isTrue(citylist.size() > 0, "query city failure");
	}

	@Test
	public void querySingle() {
		CityVO city = cityService.find(2L);
		isTrue(null != city, "query single city failure");
	}

	@Test
	public void queryByUnityCode() {
		CityVO city = cityService.find("d_c_0001");
		isTrue(null != city, "query city by unityCode failure");
	}
	
	@Test
	public void queryByPage() {
		PageParams<CityVO> pp = new PageParams<>();
		CityVO conditions = null;
		pp.setParams(conditions);
		PageThreadLocal.setParams(pp);
		PageModel<CityVO> page = cityService.findByPage(conditions);
		isTrue(page.getTotal()>0,"query city by page failure");
	}
}
