package com.jd.seed.exercise.distributed.job.context;
/**
 * <pre>
 *  ZooKeeper配置类
 *  
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午4:29:37
 */
public class ZooKeeperConfig {
	private String rootPath;
	private String connectString;

	public String getConnectString() {
		return connectString;
	}

	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
}
