package com.jd.seed.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.seed.base.SystemException;
import com.jd.seed.base.domain.Entity;
import com.jd.seed.base.domain.ValueObject;
import com.jd.seed.base.repository.CommonRepository;
import com.jd.seed.base.service.CommonService;

/**
 * <pre>
 * 通用service
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:30:31
 */
public abstract class CommonServiceImpl<ID extends Serializable, E extends Entity<ID, V>, V extends ValueObject<ID, E>, R extends CommonRepository<ID, E>>
		implements CommonService<ID, V> {
	//log
	private Logger COMMON_SERVICE_LOG = LoggerFactory.getLogger(CommonServiceImpl.class);
	// ValueObject class simpleName
	protected String voSimpleName;
	// ValueObject对应的类原型
	protected Class<V> voClass;
	protected Class<E> entityClass;
	private CommonRepository<ID, E> repository;

	@SuppressWarnings("unchecked")
	public CommonServiceImpl() {
		Class<?> thisClass = getClass();
		// 通过泛型获得继承类的类原型
		entityClass = (Class<E>) ((ParameterizedType) thisClass.getGenericSuperclass()).getActualTypeArguments()[1];
		voClass = (Class<V>) ((ParameterizedType) thisClass.getGenericSuperclass()).getActualTypeArguments()[2];
		// 获得继承类的类名
		voSimpleName = voClass.getSimpleName();
	}

	@Override
	public ID create(V vo) throws SystemException {
		if (null == vo) {
			String errorMsg = "create ojbect of " + this.voSimpleName + " error:v is null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		if (!(vo instanceof ValueObject)) {
			String errorMsg = "create object of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ ValueObject.class;
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		E entity = vo.toEntity();
		if (null == entity) {
			String errorMsg = "create object of " + this.voSimpleName + " error:method toEntity return null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}

		if (null == entity.getCreateTime()) {
			entity.createTime(new Date());
		}
		this.repository.insert(entity);
		if (null == entity.getId()) {
			String errorMsg = "create object of " + this.voSimpleName + " error: return id is null add failure";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		return entity.getId();
	}

	@Override
	public boolean modify(V vo) throws SystemException {
		if (null == vo) {
			String errorMsg = "modify ojbect of " + this.voSimpleName + " error:v is null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}

		if (!(vo instanceof ValueObject)) {
			String errorMsg = "modify ojbect of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ ValueObject.class;
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		if (null == vo.getUpdateTime()) {
			vo.updateTime(new Date());
		}
		E entity = vo.toEntity();
		if (null == entity) {
			String errorMsg = "modify ojbect of " + this.voSimpleName + " error:method toEntity return null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		return this.repository.update(entity);
	}

	@Override
	public boolean remove(V vo) throws SystemException {
		if (null == vo) {
			String errorMsg = "remove ojbect of " + this.voSimpleName + " ojbect error:vm is null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		if (!(vo instanceof ValueObject)) {
			String errorMsg = "remove ojbect of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ ValueObject.class;
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		E entity = vo.toEntity();
		if (null == entity) {
			String errorMsg = "remove ojbect of " + this.voSimpleName + " error:method toEntity return null";
			COMMON_SERVICE_LOG.error(errorMsg);
			throw new SystemException(errorMsg);
		}
		return repository.delete(entity);
	}

	@Override
	public V query(ID id) {
		V v = null;
		COMMON_SERVICE_LOG.info("get ojbect of " + this.voSimpleName + " by id=" + id);
		if (null == id) {
			 String msg = "get ojbect of " + this.voSimpleName + " by id error:id is null";
			COMMON_SERVICE_LOG.warn(msg);
			return v;
		}
		E entity = repository.selectById(id);
		if (null != entity) {
			v = entity.toVO();
		}
		return v;
	}

	@Override
	public List<V> query() {
		List<E> entitylist = repository.selectAll();
		if (entitylist.isEmpty()) {
			return new ArrayList<V>();
		}
		List<V> volist = new ArrayList<V>();
		for (E entity : entitylist) {
			volist.add(entity.toVO());
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	protected R repository() {
		return (R) repository;
	}

	public void setRepository(CommonRepository<ID, E> repository) {
		this.repository = repository;
	}
}
