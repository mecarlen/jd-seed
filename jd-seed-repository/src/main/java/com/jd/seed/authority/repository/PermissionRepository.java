package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.PermissionEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 授权
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午3:53:58
 */
@Repository
public interface PermissionRepository extends BaseRepository<Long, PermissionEntity> {

}
