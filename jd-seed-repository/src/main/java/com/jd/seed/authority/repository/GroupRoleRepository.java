package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.GroupRoleEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 用户组-角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午3:56:55
 */
@Repository
public interface GroupRoleRepository extends BaseRepository<Long, GroupRoleEntity> {

}
