package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午1:34:30
 */
@Repository
public interface RoleRepository extends BaseRepository<Long, RoleEntity>{

}
