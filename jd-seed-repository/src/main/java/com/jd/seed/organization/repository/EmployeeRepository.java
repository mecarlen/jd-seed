package com.jd.seed.organization.repository;

import org.springframework.stereotype.Repository;

import com.jd.seed.base.repository.BaseRepository;
import com.jd.seed.organization.domain.EmployeeEntity;

/**
 * <pre>
 * 员工
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月5日 下午4:12:14
 */
@Repository
public interface EmployeeRepository extends BaseRepository<Long, EmployeeEntity> {

}
