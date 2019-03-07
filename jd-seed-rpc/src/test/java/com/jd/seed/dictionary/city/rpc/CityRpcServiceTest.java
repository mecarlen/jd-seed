package com.jd.seed.dictionary.city.rpc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.jd.seed.ApiMethodFactory;
import com.jd.seed.RpcServiceBaseTest;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.base.rpc.security.SignBuilder;
import com.jd.seed.base.rpc.security.encrypt.EncryptUtils;
import com.jd.seed.base.rpc.security.encrypt.EnumEncryptionType;
import com.jd.seed.dictionary.city.City;
import com.jd.seed.dictionary.city.CityDTO;
import com.jd.seed.util.security.DESCoder;

/**
 * <pre>
 * junit city rpc service
 * 
 * </pre>
 * 
 * @author mecarlen 2018年4月1日 下午1:00:55
 */

public class CityRpcServiceTest extends RpcServiceBaseTest{
	@Resource
	private CityRpcService cityRpcService;

	@Test
	public void queryByUnityCode() {
		RpcRequest<String> request = new RpcRequest<String>();
		request.setAppCode("A10001");
		request.setServiceCode("dict_1001");
		request.setTimestamp(String.valueOf(new Date().getTime()));
		request.setParams("d_c_0001");
		String securityKey = "c503580232444d73bf6f292f965d4132";
		request.setSign(SignBuilder.buildSign(request, securityKey));
		System.out.println(JSON.toJSONString(request));
//		RpcResponse<City> response = cityRpcService.queryCity(request);
//		Assert.isTrue(response.isSuccess(), response.getErrorMsg());
	}

	@Test
	public void query() {
		RpcRequest<?> request = new RpcRequest<>();
		request.setAppCode("A10001");
		request.setServiceCode("dict_1002");
		request.setTimestamp(String.valueOf(new Date().getTime()));
		String securityKey = "c503580232444d73bf6f292f965d4132";
		request.setSign(SignBuilder.buildSign(request, securityKey));
		System.out.println("----->" + JSON.toJSONString(request));
		RpcResponse<List<City>> response = cityRpcService.queryAllCity(request);
		Assert.isTrue(response.isSuccess(), response.getErrorMsg());
	}

	@Test
	public void des() throws Exception {
		Long startTime = new Date().getTime();
		DESCoder.encrypt("13912345678", "965d4132");
		Long endTime = new Date().getTime();
		System.out.println("-------------costTime:" + (endTime - startTime));
	}

	@Test
	public void encrypt() {
		RpcRequest<String> request = new RpcRequest<String>();
		request.setAppCode("A10001");
		request.setServiceCode("dict_1001");
		request.setTimestamp(String.valueOf(new Date().getTime()));
		request.setParams("d_c_0001");
		String securityKey = "c503580232444d73bf6f292f965d4132";
		request.setSign(SignBuilder.buildSign(request, securityKey));
		cityRpcService.queryCity(request);

		CityDTO city = new CityDTO.Builder("江东", "d_c_0099").enName("JIANDONG").zhFullPin("JianDong").zhShortPin("JD")
				.createTime(new Date()).updateTime(new Date()).build();
		long startTime = new Date().getTime();
		for(int i=1;i<=100;i++){
			City  c = CityDTO.getInstance(city);
			EncryptUtils.encrypt(EnumEncryptionType.DEFAULT, request, c);
		}
		long endTime = new Date().getTime();
		System.out.println("-------->costTime:"+(endTime-startTime));
	}
	
	@Test
	public void buildMethod(){
		String classFullName = "com.jd.seed.dictionary.city.rpc.CityRpcService";
		List<String> methods = ApiMethodFactory.getServiceMethodList(classFullName);
		Assert.isTrue(methods.size()>0,"buildMehtod failure");
	}
}
