package com.jd.seed.dictionary.city.rpc;

import java.util.List;

import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.dictionary.city.City;

/**
 * <pre>
 * 城市对外服务
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午5:03:43
 */
public interface CityRpcService {

	/**
	 * <pre>
	 * 根据统一编码取城市
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            RpcRequest<String>
	 * @return RpcResponse<City>
	 */
	RpcResponse<City> queryCity(RpcRequest<String> request);

	/**
	 * <pre>
	 * 取所有城市列表
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            RpcRequest<?>
	 * @return RpcResponse<List<City>>
	 */
	RpcResponse<List<City>> queryAllCity(RpcRequest<?> request);
}
