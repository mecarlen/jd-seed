package com.jd.seed.authority.query;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.RoleEntity;
import com.jd.seed.authority.domain.RoleVO;
import com.jd.seed.base.query.BaseQuery;

/**
 * <pre>
 * 角色
 * 
 * </pre>
 * 
 * @author mecarlen 2019年2月28日 下午1:29:28
 */
@Repository
public interface RoleQuery extends BaseQuery<Long, RoleEntity, RoleVO> {

}
