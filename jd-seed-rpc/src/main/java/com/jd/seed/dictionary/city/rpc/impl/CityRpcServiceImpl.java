package com.jd.seed.dictionary.city.rpc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jd.seed.base.rpc.EnumRpcResponseState;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.base.rpc.security.authentication.Authentication;
import com.jd.seed.dictionary.city.City;
import com.jd.seed.dictionary.city.CityDTO;
import com.jd.seed.dictionary.city.rpc.CityRpcService;
import com.jd.seed.dictionary.domain.CityVO;
import com.jd.seed.dictionary.service.CityService;

/**
 * <pre>
 * 城市RPC
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月1日 上午11:49:35
 */
@Service("cityRpcService")
public class CityRpcServiceImpl implements CityRpcService {
	// logger
	private static Logger CITY_RPC_SERVICE_LOG = LoggerFactory.getLogger(CityRpcServiceImpl.class);
	@Resource
	private CityService cityService;

	@Override
	@Authentication(serviceCode = "dict_1001")
	public RpcResponse<City> queryCity(RpcRequest<String> request) {
		RpcResponse<City> response = new RpcResponse<City>();
		if (StringUtils.isBlank(request.getParams())) {
			StringBuilder errorMsg = new StringBuilder();
			errorMsg.append("rpc service queryCity failure,params is empty!");
			CITY_RPC_SERVICE_LOG.warn(errorMsg.toString());
			response.state(EnumRpcResponseState.PAREXCEPTION).errorMsg(errorMsg.toString());
			return response;
		}
		CityVO city = cityService.find(request.getParams());
		return response.result(CityDTO.getInstance(city));
	}

	@Override
	@Authentication(serviceCode = "dict_1002")
	public RpcResponse<List<City>> queryAllCity(RpcRequest<?> request) {
		RpcResponse<List<City>> response = new RpcResponse<List<City>>();
		List<CityVO> vlist = cityService.find();
		List<City> citylist = new ArrayList<City>();
		vlist.forEach(city -> citylist.add(CityDTO.getInstance(city)));
		response.result(citylist);
		return response;
	}
	
}
