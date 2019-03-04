package com.jd.seed.dictionary.query;

import org.springframework.stereotype.Repository;

import com.jd.seed.base.query.BaseQuery;
import com.jd.seed.dictionary.domain.CityEntity;
import com.jd.seed.dictionary.domain.CityVO;

/**
 * <pre>
 * 城市检索
 * 
 * </pre>
 * 
 * @author mecarlen 2018年9月29日 下午4:59:47
 */
@Repository
public interface CityQuery extends BaseQuery<Long, CityEntity,CityVO> {
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
