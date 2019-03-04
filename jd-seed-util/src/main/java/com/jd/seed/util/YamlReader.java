package com.jd.seed.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

/**
 * <pre>
 * Yaml配置读取
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月26日 下午8:33:59
 */
public class YamlReader {
	private static Logger YAML_READER_LOGGER = LoggerFactory.getLogger(YamlReader.class);

	/**
	 * <pre>
	 * 读取yaml并解析成指定类型 < T > 对象
	 * </pre>
	 * 
	 * @param fileName
	 *            String 文件名
	 * @param clazz
	 *            Class<T> 类
	 * @return <T> T
	 */
	public static <T> T readYaml(String fileName, Class<T> clazz) {
		if (StringUtils.isBlank(fileName) || null == clazz) {
			YAML_READER_LOGGER.warn("read yaml file exception,the parameters of fileName or clazz is empty");
			return null;
		}
		try {
			CustomClassLoaderConstructor constructor = new CustomClassLoaderConstructor(clazz.getClassLoader());
			Yaml yaml = new Yaml(constructor);
			T config = yaml.loadAs(YamlReader.class.getClassLoader().getResourceAsStream(fileName), clazz);
			return config;
		} catch (Exception ex) {
			YAML_READER_LOGGER.error("read yaml file exception", ex);
		}
		return null;
	}
}
