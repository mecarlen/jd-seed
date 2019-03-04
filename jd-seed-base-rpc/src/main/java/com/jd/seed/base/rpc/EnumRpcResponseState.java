package com.jd.seed.base.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * API返回状态
 * 
 * SUCCESS 200 success
 * BIZEXCEPTION 400 business exception
 * AUTEXCEPTION 401 authenication exception
 * PAREXCEPTION 402 parameters exception
 * SYSEXCEPTION 500 system exception
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午5:26:47
 */
public enum EnumRpcResponseState {
	/** 200-SUCCESS */
	SUCCESS("200", "success"),
	/** 400-BIZEXCEPTION */
	BIZEXCEPTION("400", "business exception"),
	/** 401-AUTEXCEPTION */
	AUTEXCEPTION("401", "authenication exception"),
	/** 402-PAREXCEPTION */
	PAREXCEPTION("402","parameters exception"),
	/** 500-SYSEXCEPTION */
	SYSEXCEPTION("500", "system exception");

	final private String code;
	final private String alias;
	final private static Map<String, EnumRpcResponseState> enumState = new HashMap<String, EnumRpcResponseState>();

	static {
		for (EnumRpcResponseState state : EnumRpcResponseState.values()) {
			enumState.put(state.code, state);
		}
	}

	private EnumRpcResponseState(String code, String alias) {
		this.code = code;
		this.alias = alias;
	}

	public String code() {
		return code;
	}

	public String alias() {
		return alias;
	}

	final public static EnumRpcResponseState getInstance(String code) {
		return enumState.get(code);
	}
}
