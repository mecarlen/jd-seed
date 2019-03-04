package com.jd.seed.authority.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.GroupEntity;
import com.jd.seed.base.repository.BaseRepository;

/**
 * <pre>
 * 用户组
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月27日 下午3:55:27
 */
@Repository
public interface GroupRepository extends BaseRepository<Long, GroupEntity> {

}
