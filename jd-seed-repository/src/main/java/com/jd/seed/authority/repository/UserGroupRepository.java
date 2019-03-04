package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.UserGroupEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 用户-用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午3:58:54
 */
@Repository
public interface UserGroupRepository extends BaseRepository<Long, UserGroupEntity> {

}
