package com.jd.seed.base.rpc.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jd.seed.base.rpc.AuthenicationContext;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.base.rpc.security.authentication.Authentication;

/**
 * <pre>
 * 验证工具类
 * 
 * </pre>
 * 
 * @author mecarlen 2018年3月27日 下午1:49:13
 */
@Component
public class Validator {
	// log
	private static Logger API_VALIDATOR_LOGGER = LoggerFactory.getLogger(Validator.class);
	// 时间戳服务器端与客户端相差不能超过10秒
	final public static long DIFF_TIMESTAMP = 10000L;

	public boolean verify(Authentication auth, RpcRequest<?> request) {
		// 方法权限校验
		if (!auth.serviceCode().equals(request.getServiceCode())) {
			// 请求方法编号与方法编号不匹配权限验证不通过
			API_VALIDATOR_LOGGER.warn("no permission " + auth.serviceCode() + "!=" + request.getServiceCode());
			return false;
		}

		if (null == AuthenicationContext.getAppAuths(request.getAppCode()) || !AuthenicationContext
				.getAppAuths(request.getAppCode()).getServicelist().contains(auth.serviceCode())) {
			// appCode未授权或服务编号对应未授权返回权限验证不通过
			API_VALIDATOR_LOGGER.warn("no permission appCode=" + request.getAppCode() + ",serivceCode="
					+ auth.serviceCode() + ",serviceCodelist="
					+ AuthenicationContext.getAppAuths(request.getAppCode()).getServicelist().toString());
			return false;
		}
		// 防止时间戳被重复使用，当前时间与之不能相差5秒
		Long currentTime = new Date().getTime();
		Long timestamp = Long.valueOf(request.getTimestamp());
		if (timestamp > currentTime || (currentTime - timestamp) > DIFF_TIMESTAMP) {
			// 时间戳代表时间不能超过服务器时间
			API_VALIDATOR_LOGGER
					.warn("时间戳异常，timestamp=" + request.getTimestamp() + ",currentTime=" + String.valueOf(currentTime));
			// return false;
		}
		// 签名验证
		if (!verifySign(request)) {
			return false;
		}
		return true;
	}

	/**
	 * <pre>
	 * 签名验证
	 * 
	 * 描述
	 * 1、参与签名字段timestamp,params按参数名
	 * </pre>
	 * 
	 * @param request
	 *            ApiRequest<?>
	 * @return boolean
	 */
	private boolean verifySign(RpcRequest<?> request) {
		try {
			String sign = SignBuilder.buildSign(request,
					AuthenicationContext.getAppAuths(request.getAppCode()).getSecurityKey());
			return request.getSign().equals(sign);
		} catch (Exception ex) {
			API_VALIDATOR_LOGGER.error("buildSignStr exception", ex);
			return false;
		}
	}

}
