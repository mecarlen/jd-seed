package com.jd.seed;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jd.seed.dictionary.city.domain.CityEntity;
import com.jd.seed.dictionary.city.repository.CityRepository;

/**
 * <pre>
 * 仓储junit
 * 
 * </pre>
 *
 * @author mecarlen 2017年11月21日 上午11:43:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = { "classpath:applicationContext-repository-test.xml" })
public class JunitRepository {
	@Resource
	private CityRepository cityRepository;

	@Test
	public void insert() {
		CityEntity city = new CityEntity.Builder("福州", "d_c_0302").zhFullPin("FuZhou").zhShortPin("FZ")
				.build().priority(32).enName("FUZHOU");
		cityRepository.insert(city);
		System.out.println(city.getId()+"---->");
		isTrue(city.getId() > 0, "create city failure");
	}

//	@Test
	public void update() {
		CityEntity city = cityRepository.selectByUnityCode("d_c_0301");
		city.priority(3);
		city.enName("XIAMEN");
		isTrue(cityRepository.update(city), "modify city failure");
	}

	@Test
	public void delete() {
		CityEntity city = cityRepository.selectById(14L);
		isTrue(cityRepository.delete(city), "remove city failure");
	}

	@Test
	public void selectSingle() {
		String unityCode = "d_c_0001";
		CityEntity city = cityRepository.selectByUnityCode(unityCode);
		isTrue(city != null, "search single failure");
	}

	@Test
	public void selectAll() {
		List<CityEntity> citylist = cityRepository.selectAll();
		isTrue(citylist.size() > 0, "failure");
	}
}
