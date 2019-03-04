package com.jd.seed.util.mail;

import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * 邮件模板类
 * </p>
 * 
 * @author metanoia.lang 2012-4-24
 * @author mecarlen 2018年3月15日 下午7:54:17
 */
public class SendMailTemplate {
	private String	name;
	private String	subjectTemplate;
	private String	contentTemplate;

	public SendMailTemplate(String name, String subjectTemplate,
			String contentTemplate) {
		this.name = name;
		this.subjectTemplate = subjectTemplate;
		this.contentTemplate = contentTemplate;
	}

	/**
	 * 
	 * @return String 模板邮件内容
	 */
	public String contentTemplate() {
		return contentTemplate;
	}

	/**
	 * 
	 * @return String 模板名称
	 */
	public String getName() {
		return name;
	}

	public static String paddingContent(String content,
			Map<String, String> padding) {
		return paddingTemplate(content, padding);
	}

	public static String paddingContent(String content, String key, String value) {
		return paddingTemplate(content, key, value);
	}

	public static String paddingSubject(String subject,
			Map<String, String> padding) {
		return paddingTemplate(subject, padding);
	}

	public static String paddingSubject(String subject, String key, String value) {
		return paddingTemplate(subject, key, value);
	}

	/**
	 * <p>
	 * 填充邮件模板
	 * </p>
	 * 
	 * @param template
	 *            String 邮件模板串
	 * @param valueMap
	 *            Map<String,String> 填充参数 key-value对
	 * @return String 填充后邮件内容
	 */
	private static String paddingTemplate(String template,
			Map<String, String> valueMap) {
		if (template == null || valueMap == null || valueMap.isEmpty()) {
			return template;
		}

		String ft = null;
		for (Entry<String, String> entry : valueMap.entrySet()) {
			ft = template.replace(entry.getKey(), entry.getValue());
		}
		return ft;
	}

	/**
	 * <p>
	 * 填充邮件模板
	 * </p>
	 * 
	 * @param template
	 *            String 邮件模板串
	 * @param key
	 *            String 填充占位符
	 * @param value
	 *            填充值
	 * @return String 填充后邮件内容
	 */
	private static String paddingTemplate(String template, String key,
			String value) {
		if (template == null || key == null) {
			return template;
		}

		return template.replace(key, value);
	}

	/**
	 * 
	 * @return String 模板邮件标题
	 */
	public String subjectTemplate() {
		return subjectTemplate;
	}
}
