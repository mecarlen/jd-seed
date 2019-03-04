package com.jd.seed.dictionary.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.base.repository.BaseRepository;
import com.jd.seed.dictionary.domain.CityEntity;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 *
 * @author mecarlen 2017年11月21日 上午11:09:43
 */

@Repository
public interface CityRepository extends BaseRepository<Long, CityEntity> {
}
