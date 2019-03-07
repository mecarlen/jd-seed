package com.jd.seed;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.jd.seed.authority.domain.MenuEntity;
import com.jd.seed.authority.query.MenuQuery;
import com.jd.seed.authority.query.MenuSearch;
import com.jd.seed.dictionary.domain.CityEntity;
import com.jd.seed.dictionary.query.CityQuery;
import com.jd.seed.dictionary.query.CitySearch;
import com.jd.seed.dictionary.repository.CityRepository;

/**
 * <pre>
 * ES
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月7日 下午3:45:50
 */
public class JunitES extends RepositoryBaseTest {
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	@Resource
	private CitySearch citySearch;
	@Resource
	private CityQuery cityQuery;
	@Resource
	private CityRepository cityRepository;
	@Resource
	private MenuSearch menuSearch;
	@Resource
	private MenuQuery menuQuery;
	@Test
	public void createIndex() {
		boolean exists = elasticsearchTemplate.indexExists(CityEntity.class);
		boolean result = false;
		if(!exists) {
//			result = elasticsearchTemplate.deleteIndex(MenuEntity.class);
			result = elasticsearchTemplate.createIndex(CityEntity.class);
		}
		isTrue(exists || result, "es create index failure");
	}
	
	@Test
	public void addDoc() {
		List<MenuEntity> menulist = menuQuery.selectAll();
		menuSearch.save(menulist);
	}
	
	@Test
	public void findOne() {
		MenuEntity city = menuSearch.findOne(1L);
		isTrue(city != null, "es search failure");
	}
	
	@Test
	public void search() {
		List<CityEntity> elist = citySearch.findByZhName("州");
//		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//		queryBuilder.should(QueryBuilders.matchQuery("name", "成"));
//		queryBuilder.should(QueryBuilders.matchQuery("descr", "成"));
//		queryBuilder.minimumNumberShouldMatch(1);
//		Pageable pageable = null;
//		SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
//		menuSearch.search(query);
		isTrue(elist.size()>0,"es search failure");
	}
	
}
