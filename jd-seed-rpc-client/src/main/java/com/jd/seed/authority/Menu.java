package com.jd.seed.authority;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 模块 / 功能 / 菜单
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月23日 下午8:49:50
 */
public interface Menu extends Resource {
	/** 类型-菜单-0 */
	final public static int MENU_TYPE_CODE = 0;
	/** 类型-功能-1 */
	final public static int FUNCTION_TYPE_CODE = 1;

	String getSn();

	Menu getParent();

	String getUrl();

	Integer getSequence();

	String getDescr();

	Integer getType();

	/**
	 * <pre>
	 * 菜单类型
	 * 
	 * MENU-菜单
	 * FUNCTION-功能
	 * </pre>
	 */
	public static enum Type {
		/** 左侧菜单栏 */
		MENU(Menu.MENU_TYPE_CODE, "菜单"),
		/** 页面功能点 */
		FUNCTION(Menu.FUNCTION_TYPE_CODE, "功能");
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
