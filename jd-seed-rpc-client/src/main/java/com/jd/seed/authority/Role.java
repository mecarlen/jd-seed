package com.jd.seed.authority;

import java.util.HashMap;
import java.util.Map;

import com.jd.seed.base.domain.Entity;

/**
 * <pre>
 * 角色
 * 
 * 系统角色 / 流程角色 / 业务角色
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:53:49
 */
public interface Role extends Entity<Long> {
	/** 角色类型-系统-0 */
	final public static int SYSTEM_ROLE_CODE = 0;
	/** 角色类型-流程-1 */
	final public static int PROCESS_ROLE_CODE = 1;
	/** 角色类型-业务-2 */
	final public static int BUSINESS_ROLE_CODE = 2;

	String getName();

	String getUnityCode();

	Integer getType();

	String getDescr();

	/**
	 * <pre>
	 * 角色类型
	 * 
	 * SYSTEM 系统角色
	 * PROCESS 流程角色
	 * BUSINESS 业务角色
	 * </pre>
	 * 
	 */
	public enum Type {
		/** 系统角色 */
		SYSTEM(Role.SYSTEM_ROLE_CODE, "系统角色"),
		/** 流程角色 */
		PROCESS(Role.PROCESS_ROLE_CODE, "流程角色"),
		/** 业务角色 */
		BUSINESS(Role.BUSINESS_ROLE_CODE, "系统角色");

		private int code;
		private String alias;

		private static Map<Integer, Type> TYPE_MAP = new HashMap<Integer, Type>();

		static {
			for (Type type : Type.values()) {
				TYPE_MAP.put(type.code, type);
			}
		}

		private Type(int code, String alias) {
			this.code = code;
			this.alias = alias;
		}

		public int code() {
			return code;
		}

		public String alias() {
			return alias;
		}

		public static Type getInstance(int code) {
			return TYPE_MAP.get(code);
		}
	}
}
