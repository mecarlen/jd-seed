package com.jd.seed.authority.query;

import org.springframework.stereotype.Repository;

import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.authority.domain.MenuVO;
import com.jd.seed.base.query.BaseQuery;

/**
 * <pre>
 * 菜单检索
 * 
 * </pre>
 * 
 * @author mecarlen 2018年9月6日 下午4:01:41
 */
@Repository
public interface MenuQuery extends BaseQuery<Long, MenuEntity, MenuVO> {

}
