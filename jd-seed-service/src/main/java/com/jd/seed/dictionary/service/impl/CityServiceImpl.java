package com.jd.seed.dictionary.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jd.seed.base.service.impl.BaseServiceImpl;
import com.jd.seed.dictionary.domain.CityEntity;
import com.jd.seed.dictionary.domain.CityVO;
import com.jd.seed.dictionary.query.CityQuery;
import com.jd.seed.dictionary.repository.CityRepository;
import com.jd.seed.dictionary.service.CityService;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:49:29
 */
@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<Long, CityEntity, CityVO, CityRepository>
		implements CityService {
	// log
	protected static Logger CITY_SERVICE_LOG = LoggerFactory.getLogger(CityServiceImpl.class);

	@Resource
	public void setCityRepository(CityRepository cityRepository) {
		super.setRepository(cityRepository);
	}
	
	@Resource
	public void setQuery(CityQuery cityQuery) {
		super.setQuery(cityQuery);
	}

	@Override
	public CityVO find(String unityCode) {
		CityEntity entity = ((CityQuery)query()).selectByUnityCode(unityCode);
		if (null == entity) {
			CITY_SERVICE_LOG
					.warn("query " + this.voSimpleName + " by unityCode=" + unityCode + "failure,instance not exsits");
			return null;
		}
		return entity.toVO();
	}
}
