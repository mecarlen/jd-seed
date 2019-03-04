package com.jd.seed;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.seed.base.ApplicationContextHolder;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.base.rpc.security.authentication.Authentication;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月24日 下午3:13:16
 */
public class ApiMethodFactory {
	// logger
	public static final Logger API_METHOD_LOG = LoggerFactory.getLogger(ApiMethodFactory.class);

	/**
	 * <pre>
	 * 通过服务类全路径反射取相应所有方法
	 * 
	 * </pre>
	 * 
	 * @param classFullName
	 *            String
	 * @return List<ApiMethodVO>
	 */
	public static List<String> getServiceMethodList(String classFullName) {
		try {
			Class<?> clazz = Class.forName(classFullName);
			Object obj = ApplicationContextHolder.getBean(clazz);
			List<String> methodlist = new ArrayList<String>();
			Method[] methods = obj.getClass().getSuperclass().getDeclaredMethods();
			for (Method method : methods) {
				Authentication auth = method.getAnnotation(Authentication.class);
				Class<?> rclz = method.getReturnType();
				Class<?>[] pclzs = method.getParameterTypes();
				if (null != auth && rclz.isAssignableFrom(RpcResponse.class)
						&& pclzs[0].isAssignableFrom(RpcRequest.class)) {
					StringBuilder mstr = new StringBuilder();
					mstr.append("method:" + method.getName());
					mstr.append(",code:" + auth.serviceCode());
					mstr.append(",verify:" + auth.verify());
					methodlist.add(mstr.toString());
				}
			}
			return methodlist;
		} catch (ClassNotFoundException ex) {
			API_METHOD_LOG.error("未找到相应服务类：" + classFullName, ex);
		}
		return new ArrayList<String>();
	}
}
