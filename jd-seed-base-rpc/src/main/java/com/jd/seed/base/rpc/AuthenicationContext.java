package com.jd.seed.base.rpc;

import com.jd.seed.base.rpc.security.authentication.AppAuthenication;
import com.jd.seed.base.rpc.security.authentication.AuthenicationConfig;
import com.jd.seed.util.YamlReader;

/**
 * <pre>
 * 授权上下文
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月3日 下午2:07:37
 */
public class AuthenicationContext {
	private static AuthenicationConfig auths;
	static {
		auths = YamlReader.readYaml("app-auth-config.yaml", AuthenicationConfig.class);
	}

	public static AppAuthenication getAppAuths(String appCode) {
		if (null != auths) {
			return auths.getAuths().get(appCode);
		}
		return null;
	}
}
