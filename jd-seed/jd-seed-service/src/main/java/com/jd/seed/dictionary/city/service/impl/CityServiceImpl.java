package com.jd.seed.dictionary.city.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jd.seed.base.service.impl.CommonServiceImpl;
import com.jd.seed.dictionary.city.domain.CityEntity;
import com.jd.seed.dictionary.city.domain.CityVO;
import com.jd.seed.dictionary.city.repository.CityRepository;
import com.jd.seed.dictionary.city.service.CityService;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:49:29
 */
@Service
public class CityServiceImpl extends CommonServiceImpl<Long, CityEntity, CityVO, CityRepository>
		implements CityService {
	// log
	private Logger CITY_SERVICE_LOG = LoggerFactory.getLogger(CityServiceImpl.class);

	@Resource
	public void setCityRepository(CityRepository cityRepository) {
		super.setRepository(cityRepository);
	}

	@Override
	public CityVO query(String unityCode) {
		CityEntity entity = this.repository().selectByUnityCode(unityCode);
		if (null == entity) {
			CITY_SERVICE_LOG
					.warn("query " + this.voSimpleName + " by unityCode=" + unityCode + "failure,instance not exsits");
			return null;
		}
		return entity.toVO();
	}

}
