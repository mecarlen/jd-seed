package com.jd.seed.dictionary.city.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jd.seed.base.repository.CommonRepository;
import com.jd.seed.dictionary.city.domain.CityEntity;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 *
 * @author mecarlen 2017年11月21日 上午11:09:43
 */

@Mapper
@Repository
public interface CityRepository extends CommonRepository<Long, CityEntity> {
	/**
	 * <pre>
	 * 根据统一标识取
	 * 
	 * </pre>
	 * 
	 * @param unityCode
	 *            String
	 * @return CityEntity
	 */
	CityEntity selectByUnityCode(String unityCode);
}
