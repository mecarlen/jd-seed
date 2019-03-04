package com.jd.seed.dictionary.city.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jd.seed.dictionary.domain.CityVO;
import com.jd.seed.dictionary.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午9:14:52
 */
@Api(description = "城市检索接口")
@RestController
@RequestMapping(value = "/dictionary/city/search", produces = { "application/json" })
public class CitySearchController {
	@Resource
	private CityService cityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation("检索所有城市列表")
	public List<CityVO> list() {
		return cityService.find();
	}

	@ApiOperation("根据城市id检索城市")
	@RequestMapping(value = "/byId/{cityId}", method = RequestMethod.GET, produces = "application/json")
	public CityVO byId(
			@ApiParam(value = "城市id", required = true) @PathVariable(required = true, name = "cityId") Long id) {
		return cityService.find(id);
	}

	@ApiOperation("根据城市统一标识检索城市")
	@RequestMapping(value = "/byUnityCode/{unityCode}", method = RequestMethod.GET)
	public CityVO byUnityCode(
			@ApiParam(value = "城市统一标识", required = true) @PathVariable(name = "unityCode", required = true) String unityCode) {

		return cityService.find(unityCode);
	}
}
