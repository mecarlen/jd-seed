package com.jd.seed.base.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 通用elasticsearch仓储
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月24日 上午10:49:38
 */
public class CommonElasticsearchRepository<ID extends Serializable, E extends Entity<ID, ?>> implements CommonRepository<ID, E> {
	
	private ElasticsearchRepository<E, ID> elasticsearchRepository;

	@Override
	public void insert(E e) {
		elasticsearchRepository.save(e);
	}

	@Override
	public boolean update(E e) {
		return false;
	}

	@Override
	public boolean delete(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E selectById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
