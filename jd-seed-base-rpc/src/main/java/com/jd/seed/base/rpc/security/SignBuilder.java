package com.jd.seed.base.rpc.security;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.util.security.SHACoder;

/**
 * <pre>
 * 签名生成器
 * 
 * </pre>
 * 
 * @author mecarlen 2018年3月27日 下午5:01:53
 */
public class SignBuilder {
	// log
	private static Logger SIGN_BUILDER_LOGGER = LoggerFactory.getLogger(SignBuilder.class);

	/**
	 * <pre>
	 * 生成签名
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            RpcRequest
	 * @param securityKey
	 *            String
	 * @param String
	 */
	public static String buildSign(final RpcRequest<?> request, final String securityKey) {
		StringBuilder signStr = new StringBuilder(buildSignStr(request)).append(securityKey);
		try {
			return SHACoder.encryptSHAString(signStr.toString().getBytes());
		} catch (NoSuchAlgorithmException ex) {
			SIGN_BUILDER_LOGGER.error("build sign exception", ex);
			return request.getTimestamp();
		}
	}

	/**
	 * <pre>
	 * 组装签名原字符串
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            RpcRequest<?>
	 * @return String
	 */
	public static String buildSignStr(RpcRequest<?> request) {
		if (null != request) {
			StringBuilder signStr = new StringBuilder(request.getTimestamp());
			if (!ObjectUtils.isEmpty(request.getParams())) {
				signStr.append(JSON.toJSONString(request.getParams()));
			}
			return signStr.toString();
		}
		return "";
	}

}
