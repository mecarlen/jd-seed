package com.jd.seed.authority.query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.jd.seed.authority.domain.MenuEntity;

/**
 * <pre>
 * 菜单检索
 * 
 * </pre>
 * 
 * @author mecarlen 2018年12月6日 下午2:42:26
 */
@Component
public interface MenuSearch extends ElasticsearchRepository<MenuEntity, Long> {
	
}
