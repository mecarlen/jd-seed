package com.jd.seed.dictionary.city.rpc;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;

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
}
