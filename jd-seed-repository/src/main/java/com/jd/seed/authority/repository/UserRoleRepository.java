package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.UserRoleEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 用户-角色 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午3:57:58
 */
@Repository
public interface UserRoleRepository extends BaseRepository<Long, UserRoleEntity> {

}
