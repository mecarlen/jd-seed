package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 菜单
 * 
 * </pre>
 * 
 * @author mecarlen 2018年7月7日 下午9:34:40
 */
@Repository
public interface MenuRepository extends BaseRepository<Long, MenuEntity> {

}
