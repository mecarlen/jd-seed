package com.jd.seed.dictionary.service;

import com.jd.seed.base.service.BaseService;
import com.jd.seed.dictionary.domain.CityVO;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午3:11:32
 */
public interface CityService extends BaseService<Long, CityVO> {
	/**
	 * <pre>
	 * 根据城市统一标识搜索
	 * 
	 * </pre>
	 * 
	 * @param unityCode
	 *            String
	 * @return CityVO
	 */
	CityVO find(String unityCode);
}
