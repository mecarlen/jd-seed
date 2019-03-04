package com.jd.seed.organization.query;

import org.springframework.stereotype.Repository;

import com.jd.seed.base.query.BaseQuery;
import com.jd.seed.organization.domain.EmployeeEntity;
import com.jd.seed.organization.domain.EmployeeVO;

/**
 * <pre>
 * 员工
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月5日 下午4:13:58
 */
@Repository
public interface EmployeeQuery extends BaseQuery<Long, EmployeeEntity,EmployeeVO> {

}
