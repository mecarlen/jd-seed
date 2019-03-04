package com.jd.seed.dictionary.query;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.jd.seed.dictionary.domain.CityEntity;

/**
 * <pre>
 * 城市检索
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月7日 下午3:28:55
 */
@Component
public interface CitySearch extends ElasticsearchRepository<CityEntity, Long>{
	
	CityEntity findByUnityCode(String unityCode);
	
	List<CityEntity> findByZhName(String zhName);
}
