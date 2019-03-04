package com.jd.seed;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.jd.seed.dictionary.domain.CityEntity;
import com.jd.seed.dictionary.query.CityQuery;
import com.jd.seed.dictionary.repository.CityRepository;

/**
 * <pre>
 * 仓储junit
 * 
 * </pre>
 *
 * @author mecarlen 2017年11月21日 上午11:43:55
 */
public class JunitCityRepository extends RepositoryBaseTest{
	@Resource
	private CityRepository cityRepository;
	@Resource
	private CityQuery cityQuery;
	@Test
	public void insert() {
		CityEntity city = new CityEntity.Builder("深圳", "d_c_0009").zhFullPin("ShenZhen").zhShortPin("SZ")
				.build().priority(9).enName("SHENZHEN");
		cityRepository.insert(city);
		isTrue(city.getId() > 0, "create city failure");
	}

//	@Test
	public void update() {
		CityEntity city = cityQuery.selectByUnityCode("d_c_0301");
		city.priority(3);
		city.enName("XIAMEN");
		isTrue(cityRepository.update(city), "modify city failure");
	}

	@Test
	public void delete() {
		CityEntity city = cityQuery.selectById(14L);
		isTrue(cityRepository.delete(city), "remove city failure");
	}

	@Test
	public void selectSingle() {
		String unityCode = "d_c_0001";
		CityEntity city = cityQuery.selectByUnityCode(unityCode);
		isTrue(city != null, "search single failure");
	}

	@Test
	public void selectAll() {
		List<CityEntity> citylist = cityQuery.selectAll();
		isTrue(citylist.size() > 0, "failure");
	}
	
}
