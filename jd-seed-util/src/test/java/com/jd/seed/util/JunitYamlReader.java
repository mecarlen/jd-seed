package com.jd.seed.util;

import static org.springframework.util.Assert.isTrue;

import org.junit.Test;

import com.jd.seed.util.yaml.AuthenicationConfig;

/**
 * <pre>
 * yaml文件读取测试类
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 上午11:45:01
 */
public class JunitYamlReader {

	@Test
	public void readSelfBean() {
		final String fileName = "app-auth-config.yaml";
		AuthenicationConfig config = YamlReader.readYaml(fileName, AuthenicationConfig.class);
		isTrue(null != config, "read yaml file successfully");
	}
	
	public void readMap(){
		
	}
	
	public void readList(){
		
	}
	
}