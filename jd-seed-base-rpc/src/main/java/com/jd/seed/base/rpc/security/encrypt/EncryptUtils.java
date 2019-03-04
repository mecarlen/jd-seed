package com.jd.seed.base.rpc.security.encrypt;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.seed.base.rpc.AuthenicationContext;
import com.jd.seed.base.rpc.RpcRequest;
import com.jd.seed.util.regex.EnumCommonRegexPattern;
import com.jd.seed.util.security.AESCoder;

/**
 * <pre>
 * 脱敏加密工具类
 * 
 * 根据不同加密方式脱敏 @see EnumEncryptionType
 * 描述
 *   1、支持String及自定义类型
 *   2、支持List集合
 *   3、技持Map集合
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月4日 上午10:32:02
 */
public class EncryptUtils {
	// log
	private static Logger ENCRYPT_LOGGER = LoggerFactory.getLogger(EncryptUtils.class);

	/**
	 * <pre>
	 * 对具体返回 - 集合对象进行加密
	 * 
	 * </pre>
	 * 
	 * @param type
	 *            EnumEncryptionType
	 * @param request
	 *            RpcRequest<?>
	 * @param datas
	 *            List<?>
	 * @return List<?>
	 */
	final public static List<?> encrypt(final EnumEncryptionType type, final RpcRequest<?> request,
			final List<?> datas) {
		if (!datas.isEmpty()) {
			List<Object> enData = new ArrayList<Object>(datas.size());
			datas.forEach(data -> {
				enData.add(encrypt(type, request, data));
			});
			return enData;
		}
		return datas;
	}

	/**
	 * <pre>
	 * 对具体返回 - Map对象进行加密
	 * 
	 * </pre>
	 * 
	 * @param type
	 *            EnumEncryptionType
	 * @param request
	 *            RpcRequest<?>
	 * @param datas
	 *            Map<String,Object>
	 * @return Map<String,?>
	 */
	final public static Map<String, ?> encrypt(final EnumEncryptionType type, final RpcRequest<?> request,
			final Map<String, ?> datas) {
		if (!datas.isEmpty()) {
			Map<String, Object> endatas = new HashMap<String, Object>();
			datas.entrySet().forEach(data -> {
				endatas.put(data.getKey(), encrypt(type, request, data.getValue()));
			});
			return endatas;
		}
		return datas;
	}

	/**
	 * <pre>
	 * 对具体返回 - 自定义对象进行加密
	 * 
	 * </pre>
	 * 
	 * @param type
	 *            EnumEncryptionType
	 * @param request
	 *            RpcRequest<?>
	 * @param data
	 *            Object
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	final public static Object encrypt(final EnumEncryptionType type, final RpcRequest<?> request, final Object data) {
		if (null == data) {
			return data;
		}
		if (data instanceof String) {
			return encrypt(type, request, (String) data);
		} else if (data instanceof List) {
			return encrypt(type, request, (List<Object>) data);
		} else if (data instanceof Map) {
			return encrypt(type, request, (Map<String, Object>) data);
		} else if (null != data.getClass().getClassLoader()) {
			// 自定义类型,当前这种判断方法可能存在风险
			List<Field> fieldlist = Arrays.asList(data.getClass().getDeclaredFields());
			fieldlist.forEach(field -> {
				if (!"serialVersionUID".equals(field.getName())) {
					EnumEncryptionType encrypType = getEncryptionType(field);
					if (null != encrypType) {
						field.setAccessible(true);
						try {
							field.set(data, encrypt(encrypType, request, field.get(data)));
						} catch (Exception ex) {
							ENCRYPT_LOGGER.error("密文覆盖明文异常", ex);
						}
					}
				}
			});
			return data;
		}
		return data;
	}

	/**
	 * <pre>
	 * 最高级别的脱敏传输方式，可逆密文
	 * 
	 * </pre>
	 * 
	 * @param type
	 *            EnumEncryptionType
	 * @param request
	 *            RpcRequest<?>
	 * @param data
	 *            String
	 * @return String
	 */
	final public static String encrypt(final EnumEncryptionType type, final RpcRequest<?> request, final String data) {
		// 字符串默认加密
		String enstr = data;

		switch (type) {
		case ENCRYPT:
			try {
				enstr = AESCoder.encrypt(data, AuthenicationContext.getAppAuths(request.getAppCode()).getSecurityKey());
			} catch (Exception ex) {
				ENCRYPT_LOGGER.error("appCode=" + request.getAppCode() + ",serviceCode=" + request.getServiceCode()
						+ "服务加密传输异常,自动切换为明文传输", ex);
			}
			break;
		case MOBILE:
			// 手机号
			if (data.matches(EnumCommonRegexPattern.MOBILE.pattern())) {

			}
			break;
		case IDCARDNO:
			// 身证ID
			if (data.matches(EnumCommonRegexPattern.IDCARDNO.pattern())) {
				
			}
			break;
		case CERTIFICATE:
			//证件号
			if (data.matches(EnumCommonRegexPattern.IDCARDNO.pattern())) {
				
			}
			break;
		default:
			enstr = "******";
			break;
		}

		return enstr;
	}

	/**
	 * <pre>
	 * 取字段加密注解
	 * 
	 * </pre>
	 * 
	 * @param field
	 *            Field
	 * @return EnumEncryptionType
	 */
	final private static EnumEncryptionType getEncryptionType(Field field) {
		Encryption encryption = field.getAnnotation(Encryption.class);
		if (null != encryption && encryption.encrypt()) {
			return encryption.type();
		}
		return null;
	}
}
