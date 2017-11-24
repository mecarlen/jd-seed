package com.jd.seed.base.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 实体状态
 * 
 * NORMAL 在用
 * DISABLE 停用
 * DEPRECATED 弃用
 * </pre>
 * 
 * @author mecarlen 2016年8月11日 上午11:48:53
 */
public enum EnumEntityState {
	/** 在用 1 */
	NORMAL(Entity.STATE_NORMAL, "在用"),
	/** 停用 0 */
	DISABLE(Entity.STATE_DISABLE, "停用"),
	/** 弃用 1 */
	DEPRECATED(Entity.STATE_DEPRECATED, "弃用");
	private int code;
	private String alias;
	final private static Map<Integer, EnumEntityState> states = new HashMap<Integer, EnumEntityState>();
	static {
		for (EnumEntityState state : EnumEntityState.values()) {
			states.put(state.code, state);
		}
	}

	private EnumEntityState(final int code, final String alias) {
		this.code = code;
		this.alias = alias;
	}

	public int code() {
		return code;
	}

	public String alias() {
		return alias;
	}

	public static EnumEntityState getInstance(final int code) {
		if (states.containsKey(code)) {
			return states.get(code);
		}
		return null;
	}
}
