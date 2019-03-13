package com.jd.seed.exercise.distributed.job;

import org.apache.curator.framework.CuratorFramework;

import com.jd.seed.exercise.distributed.job.context.ZooKeeperConfig;

/**
 * <pre>
 * zookeeper客户端工厂接口
 * 
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午4:26:13
 */
public interface ZooKeeperClientFactory {
	/**
	 * <pre>
	 * 取客户端ZooKeeper配置
	 * 
	 * </pre>
	 * @return ZooKeeperConfig
	 * */
	ZooKeeperConfig getZooKeeperConfig();
	/**
	 * <pre>
	 * 取curator客户端实例
	 * 
	 * </pre>
	 * @return CuratorFramework
	 * */
	CuratorFramework getCuratorFramework();
}
