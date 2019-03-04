package com.jd.seed.authority;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 操作
 * 
 * 授权运算 - 位或
 * 
 * 撤权运算 - 位与非
 * 
 * 鉴权运算 - 与后等于相应操作码
 * </pre>
 * 
 * @author mecarlen 2019年2月26日 上午11:10:07
 */
public class Operation {
	/** 操作-读-1 */
	final public static int READ_OPERATION_CODE = 1 << 0;
	/** 操作-增-2 */
	final public static int CREATE_OPERATION_CODE = 1 << 1;
	/** 操作-改-4 */
	final public static int UPDATE_OPERATION_CODE = 1 << 2;
	/** 操作-删-8 */
	final public static int DELETE_OPERATION_CODE = 1 << 3;

	/**
	 * <pre>
	 * 授权运算 - 或
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @param newOperation
	 *            Code 待授权操作
	 * @return int
	 */
	public static int grant(int currOperation, Code newOperation) {
		return currOperation | newOperation.code;
	}

	/**
	 * <pre>
	 * 撤权运算 - 与非
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @param operation
	 *            Code 待撤权操作
	 * @return int
	 */
	public static int revoke(int currOperation, Code operation) {

		return currOperation & (~operation.code);
	}

	/**
	 * <pre>
	 * 验证是否有查看权限
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @return boolean
	 */
	public static boolean hasRead(int currOperation) {

		return Code.READ.code == (currOperation & Code.READ.code);
	}

	/**
	 * <pre>
	 * 验证是否有新增权限
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @return boolean
	 */
	public static boolean hasCreate(int currOperation) {
		return Code.CREATE.code == (currOperation & Code.CREATE.code);
	}

	/**
	 * <pre>
	 * 验证是否有修改权限
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @return boolean
	 */
	public static boolean hasUpdate(int currOperation) {
		return Code.UPDATE.code == (currOperation & Code.UPDATE.code);
	}

	/**
	 * <pre>
	 * 验证是否有删除权限
	 * 
	 * </pre>
	 * 
	 * @param currOperation
	 *            int 当前已授权操作码或运算值
	 * @return boolean
	 */
	public static boolean hasDelete(int currOperation) {
		return Code.DELETE.code == (currOperation & Code.DELETE.code);
	}

	/**
	 * <pre>
	 * 操作码
	 * 
	 * READ 查看
	 * CREATE 增加
	 * UPDATE 修改
	 * DELETE 删除
	 * </pre>
	 */
	public static enum Code {
		/** 查看-1 */
		READ(Operation.READ_OPERATION_CODE, "查看"),
		/** 新增-2 */
		CREATE(Operation.CREATE_OPERATION_CODE, "新增"),
		/** 修改-4 */
		UPDATE(Operation.UPDATE_OPERATION_CODE, "修改"),
		/** 删除-8 */
		DELETE(Operation.DELETE_OPERATION_CODE, "删除");

		// 操作码
		private int code;
		// 操作别名
		private String alias;

		private static Map<Integer, Code> CODE_MAP = new HashMap<Integer, Code>();
		static {
			for (Code code : Code.values()) {
				CODE_MAP.put(code.code(), code);
			}
		}

		private Code(int code, String alias) {
			this.code = code;
			this.alias = alias;
		}

		public int code() {
			return code;
		}

		public String alias() {
			return alias;
		}

		public static Code getInstance(Integer code) {
			return CODE_MAP.get(code);
		}
	}
}
