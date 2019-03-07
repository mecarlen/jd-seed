package com.jd.seed.dictionary.city.rpc;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.base.rpc.security.SignBuilder;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年5月25日 下午3:53:46
 */
public class JunitApiMethodFactory {

	@Test
	public void getMethodSimpleDeclare() throws Exception {
		String classFullName = "com.jd.seed.dictionary.city.rpc.CityRpcService";
		Class<?> clazz = Class.forName(classFullName);
		List<String> methodlist = new ArrayList<String>();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Class<?> rclz = method.getReturnType();
			Class<?>[] pclzs = method.getParameterTypes();
			if (rclz.isAssignableFrom(RpcResponse.class)
					&& pclzs[0].isAssignableFrom(RpcRequest.class)) {
				StringBuilder methodDeclare = new StringBuilder(rclz.getSimpleName());
				ParameterizedType rType = (ParameterizedType)method.getGenericReturnType();
				methodDeclare.append("<"+rType.getActualTypeArguments()[0].getTypeName()+"> ");
				methodDeclare.append(method.getName()+"(");
				ParameterizedType pType = (ParameterizedType) method.getGenericParameterTypes()[0];
//				Class<?> paramClazz=(Class<?>)aType.getActualTypeArguments()[0];
				methodDeclare.append(pclzs[0].getSimpleName()+"<"+pType.getActualTypeArguments()[0].getTypeName()+">"+")");
				System.out.println(methodDeclare.toString());
				methodlist.add(methodDeclare.toString());
			}
		}
	}
	
	@Test
	public void getRequest() {
		RpcRequest<String> request = new RpcRequest<String>();
		request.setAppCode("A10001");
		request.setServiceCode("dict_1001");
		request.setTimestamp(String.valueOf(new Date().getTime()));
		request.setParams("d_c_0001");
		String securityKey = "c503580232444d73bf6f292f965d4132";
		request.setSign(SignBuilder.buildSign(request, securityKey));
		System.out.println(JSON.toJSONString(request));
	}
}
