package com.jd.seed;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jd.seed.dictionary.city.domain.CityVO;
import com.jd.seed.dictionary.city.service.CityService;

/**
 * <pre>
 * 服务层junit
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午6:02:11
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-service-test.xml" })
@SpringBootTest
public class JunitService {
	@Resource
	private CityService cityService;

	@Test
	public void query() {
		List<CityVO> citylist = cityService.query();
		isTrue(citylist.size() > 0, "query city failure");
	}

	@Test
	public void querySingle() {
		CityVO city = cityService.query(2L);
		isTrue(null != city, "query single city failure");
	}

	@Test
	public void queryByUnityCode() {
		CityVO city = cityService.query("d_c_00001");
		isTrue(null != city, "query city by unityCode failure");
	}
}
