package com.jd.seed.dictionary.city.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jd.seed.base.rpc.EnumRpcResponseState;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.dictionary.city.City;
import com.jd.seed.dictionary.city.rpc.CityRpcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <pre>
 * City REST API
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月1日 下午12:37:05
 */
@Api(value = "城市", tags = { "字典-城市接口" })
@RestController
@RequestMapping("/dictionary/city")
public class CityController {
	@Resource
	private CityRpcService cityRpcService;

	/**
	 * <pre>
	 * 根据统一编码查询城市
	 * 
	 * </pre>
	 */
	@ApiOperation(value = "根据统一编码查询城市")
	@RequestMapping(value = "/query/city/unityCode", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public RpcResponse<City> queryByUnityCode(@ApiParam @Valid @RequestBody RpcRequest<String> request,
			@ApiIgnore BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			RpcResponse<City> response = new RpcResponse<City>();
			StringBuilder errorMsg = new StringBuilder();
			bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()));
			response.state(EnumRpcResponseState.PAREXCEPTION).errorMsg(errorMsg.toString());
			return response;
		}
		return cityRpcService.queryCity(request);
	}

	/**
	 * <pre>
	 * 取所有城市列表
	 * 
	 * </pre>
	 * 
	 * */
	@ApiOperation(value = "取所有城市列表")
	@RequestMapping(value = "/query/city/fulllist", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public RpcResponse<List<City>> queryAllCity(@ApiParam @Valid @RequestBody RpcRequest<?> request,
			@ApiIgnore BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			RpcResponse<List<City>> response = new RpcResponse<List<City>>();
			StringBuilder errorMsg = new StringBuilder();
			bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()));
			response.state(EnumRpcResponseState.PAREXCEPTION).errorMsg(errorMsg.toString());
			return response;
		}
		return cityRpcService.queryAllCity(request);
	}

}
