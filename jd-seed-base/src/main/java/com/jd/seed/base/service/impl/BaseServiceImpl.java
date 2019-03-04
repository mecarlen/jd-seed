package com.jd.seed.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.seed.base.SystemException;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BaseVO;
import com.jd.seed.base.query.BaseQuery;
import com.jd.seed.base.query.PageModel;
import com.jd.seed.base.query.PageThreadLocal;
import com.jd.seed.base.repository.BaseRepository;
import com.jd.seed.base.service.BaseService;

/**
 * <pre>
 * 通用service
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午5:30:31
 */
public abstract class BaseServiceImpl<ID extends Serializable, E extends BaseEntity<ID, V>, V extends BaseVO<ID, E>, R extends BaseRepository<ID, E>>
		implements BaseService<ID, V> {
	// log
	private Logger COMMON_SERVICE_LOG = LoggerFactory.getLogger(BaseServiceImpl.class);
	// ValueObject class simpleName
	protected String voSimpleName;
	// ValueObject对应的类原型
	protected Class<V> voClass;
	protected Class<E> entityClass;
	protected R repository;
	protected BaseQuery<ID, E, V> query;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
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
		if (!(vo instanceof BaseVO)) {
			String errorMsg = "create object of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ BaseVO.class;
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

		if (!(vo instanceof BaseVO)) {
			String errorMsg = "modify ojbect of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ BaseVO.class;
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
		if (!(vo instanceof BaseVO)) {
			String errorMsg = "remove ojbect of " + this.voSimpleName + " error:" + voClass + " not extends "
					+ BaseVO.class;
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
	public List<V> find() {
		List<E> entitylist = query.selectAll();
		if (entitylist.isEmpty()) {
			return new ArrayList<V>();
		}
		List<V> volist = new ArrayList<V>();
		for (E entity : entitylist) {
			volist.add(entity.toVO());
		}
		return volist;
	}

	@Override
	public V find(ID id) {
		V v = null;
		COMMON_SERVICE_LOG.info("get ojbect of " + this.voSimpleName + " by id=" + id);
		if (null == id) {
			String msg = "get ojbect of " + this.voSimpleName + " by id error:id is null";
			COMMON_SERVICE_LOG.warn(msg);
			return v;
		}
		E entity = query.selectById(id);
		if (null != entity) {
			v = entity.toVO();
		}
		return v;
	}

	@Override
	public List<V> find(V conditions) {

		return null;
	}

	@Override
	public PageModel<V> findByPage(V conditions) {
		Page<V> pg = PageHelper.startPage(PageThreadLocal.getParams().getPageNo(),
				PageThreadLocal.getParams().getPageSize(), true);
		// PageParams<V> pp = PageThreadLocal.getParams();
		// pp.setParams(conditions);
		List<E> elist = query.select(conditions);
		PageModel<V> page = toPage(pg.toPageInfo());
		List<V> vlist = new ArrayList<V>(elist.size());
		for (E e : elist) {
			vlist.add(e.toVO());
		}
		page.setData(vlist);
		return page;
	}

	protected PageModel<V> toPage(PageInfo<V> pi) {
		PageModel<V> page = new PageModel<V>();
		page.setCurrPage(pi.getPageNum());
		page.setSize(pi.getPageSize());
		page.setTotal(Long.valueOf(pi.getTotal()).intValue());
		return page;
	}

	@SuppressWarnings("unchecked")
	public <Q extends BaseQuery<ID, E, V>> Q query() {
		return (Q) query;
	}

	public void setRepository(R repository) {
		this.repository = repository;
	}

	public void setQuery(BaseQuery<ID, E, V> query) {
		this.query = query;
	}

}
