package com.jd.seed.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Read the properties file.
 * 
 * </pre>
 * 
 * @author Metanoia.Lang
 * @author mecarlen.wang 2018年3月26日 下午8:31:05
 */
public class PropReader {
	private static Logger LOGGER = LoggerFactory.getLogger(PropReader.class);
	private Properties prop = null;

	/**
	 * <p>
	 * construction function.
	 * </p>
	 * 
	 * @param filename
	 *            String Relative path for package base path
	 */
	public PropReader(String filename) {
		InputStream is = null;
		prop = new Properties();

		LOGGER.debug("load Properties file:" + filename + "...");
		is = this.getClass().getClassLoader().getResourceAsStream(filename);

		try {
			prop.load(is);
			LOGGER.debug("init Properties Object success");

			if (is != null) {
				is.close();
			}
			LOGGER.debug("close load Properties file:" + filename + " success");
		} catch (IOException e) {
			LOGGER.debug("load Properties file:" + filename + " IO error",e);
		}
	}

	/**
	 * <p>
	 * get value of key from property
	 * </p>
	 * 
	 * @param key
	 *            String property key
	 * @return String value of key
	 */
	public String getValue(String key) {
		return (prop.getProperty(key));
	}
}
