package com.jd.seed;

import java.util.Collection;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jd.seed.base.rpc.EnumRpcResponseState;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.RpcResponse;
import com.jd.seed.base.rpc.security.Validator;
import com.jd.seed.base.rpc.security.authentication.Authentication;
import com.jd.seed.base.rpc.security.encrypt.EncryptUtils;
import com.jd.seed.base.rpc.security.encrypt.EnumEncryptionType;

/**
 * <pre>
 * 鉴权AOP
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午5:12:18
 */
@Component
@Aspect
@Order(1)
public class RpcSecurityAuthenticationAspect {
	// log
	private static Logger API_SA_VERIFY_LOGGER = LoggerFactory.getLogger(RpcSecurityAuthenticationAspect.class);
	// validator
	@Autowired
	private Validator validator;

	/**
	 * <pre>
	 * 安全及权限验证
	 * 
	 * </pre>
	 */
	@SuppressWarnings("rawtypes")
	@Around("execution(public com.jd.seed.base.rpc.RpcResponse com.jd.seed..rpc.*RpcService.*(com.jd.seed.base.rpc.RpcRequest)) && args(request) && @annotation(auth)")
	public Object verify(ProceedingJoinPoint pjp, RpcRequest<?> request, Authentication auth) {
		try {
			// 方法鉴权判断
			if (!auth.verify()) {
				// 申名式无需鉴权
				return encrypt(request, (RpcResponse) pjp.proceed());
			}
			// 具体鉴权
			if (validator.verify(auth, request)) {
				// 权限认证通过
				return encrypt(request, (RpcResponse) pjp.proceed());
			}
			RpcResponse<?> response = new RpcResponse<>();
			response.errorMsg(EnumRpcResponseState.AUTEXCEPTION.alias()).state(EnumRpcResponseState.AUTEXCEPTION);
			return response;
		} catch (Throwable ex) {
			API_SA_VERIFY_LOGGER.error("接口安全及权限验证异常", ex);
			RpcResponse<?> response = new RpcResponse<>();
			response.state(EnumRpcResponseState.SYSEXCEPTION)
					.errorMsg(EnumRpcResponseState.SYSEXCEPTION.alias() + "," + ex.getMessage());
			return response;
		}
	}

	/**
	 * <pre>
	 * 针对成功返回的业务数据进行加密处理
	 * 
	 * 描述
	 * 1、支持String及自定义类型
	 * 2、支持List集合
	 * 3、技持Map集合
	 * </pre>
	 * 
	 * @param request
	 *            RpcRequest<?>
	 * @param response
	 *            RpcResponse<?>
	 * @return RpcResponse
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RpcResponse encrypt(final RpcRequest<?> request, final RpcResponse response) {

		if (response.getResult() instanceof String) {
			response.result(
					EncryptUtils.encrypt(EnumEncryptionType.ENCRYPT, request, (String) response.getResult()));
		} else if (response.getResult() instanceof Collection) {
			response.result(EncryptUtils.encrypt(EnumEncryptionType.ENCRYPT, request,
					(Collection<Object>) response.getResult()));
		} else if (response.getResult() instanceof Map) {
			response.result(EncryptUtils.encrypt(EnumEncryptionType.ENCRYPT, request,
					(Map<String, Object>) response.getResult()));
		} else if (null != response.getResult().getClass().getClassLoader()) {
			// 自定义类型,当前这种判断方法可能存在风险
			EncryptUtils.encrypt(null, request, response.getResult());
		}
		return response;
	}

}
