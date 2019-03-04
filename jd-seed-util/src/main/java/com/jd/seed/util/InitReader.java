package com.jd.seed.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * this class is used to read the ini file and return the required value.
 * 
 * </pre>
 * 
 * @author Jin Xin
 * @author Metanoia.Lang
 * @author mecarlen.wang 2018年3月26日 下午8:27:20
 */
public class InitReader {
	private static Logger LOGGER = LoggerFactory.getLogger(InitReader.class);

	private Map<String, Properties> sections = new HashMap<String, Properties>();
	private String currentSecion;
	private Properties current;

	/**
	 * <p>
	 * construction function.
	 * </p>
	 * 
	 * @param filename
	 *            String Relative path for package base path
	 */
	public InitReader(String filename) {
		LOGGER.debug("load ini file:" + filename + "...");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			LOGGER.error("init IniReader error", e1);
		}

		try {
			read(reader);
			LOGGER.debug("init IniReader success");
		} catch (IOException e) {
			LOGGER.error("init IniReader IO error", e);
		}

		if (null != reader) {
			try {
				reader.close();
				LOGGER.debug("close ini file:" + filename + " success");
			} catch (IOException e) {
				LOGGER.error("close ini file:" + filename + " IO error", e);
			}
		}
	}

	/**
	 * <p>
	 * get value by name([]) and name(key)
	 * </p>
	 * 
	 * @param section
	 *            String section name
	 * @param name
	 *            String key name
	 * @return value null if section is null or name is null or value is null
	 */
	public String getValue(String section, String name) {
		if (null == name || "".equals(name)) {
			return null;
		} else {
			Properties p = sections.get(section);
			if (p == null) {
				return null;
			}
			return p.getProperty(name);
		}
	}

	private void parseLine(String line) {
		line = line.trim();
		if (line.matches("\\[.*\\]")) {
			currentSecion = line.replaceFirst("\\[(.*)\\]", "$1");
			current = new Properties();
			sections.put(currentSecion, current);
		} else if (line.matches(".*=.*")) {
			if (current != null) {
				int i = line.indexOf('=');
				String name = line.substring(0, i);
				String value = line.substring(i + 1);
				current.setProperty(name, value);
			}
		}
	}

	private void read(BufferedReader reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null) {
			parseLine(line);
		}
	}
}
