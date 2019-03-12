package com.jd.seed.exercise.distributed.job;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.jd.seed.exercise.distributed.job.context.ZooKeeperConfig;

/**
 * <pre>
 *  ZooKeeper客户端工厂类
 *  
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午4:32:34
 */
public class SimpleCuratorFrameworkFactory implements ZooKeeperClientFactory,InitializingBean{
	private ZooKeeperConfig zooKeeperConfig;
	private CuratorFramework curatorFramework;
	private ConnectionStateListener connectionStateListener;
	
	private void createCuratorFramework() {
        Assert.state(this.zooKeeperConfig != null, "zooKeeperConfig must be set when using 'zooKeeperConfig'");
        curatorFramework = CuratorFrameworkFactory.newClient(zooKeeperConfig.getConnectString(), new ExponentialBackoffRetry(1000, 3));
        if (connectionStateListener != null) {
            curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);
        }
    }
	
	public void start() {
		if(curatorFramework!=null){
			synchronized(CuratorFramework.class){
				if(curatorFramework!=null){
					curatorFramework.start();
				}
			}
		}
    }

    public void close() {
        CloseableUtils.closeQuietly(curatorFramework);
    }
	
	public void afterPropertiesSet() throws Exception {
		createCuratorFramework();
	}

	public ZooKeeperConfig getZooKeeperConfig() {
		return zooKeeperConfig;
	}

	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}

	public void setZooKeeperConfig(ZooKeeperConfig zooKeeperConfig) {
		this.zooKeeperConfig = zooKeeperConfig;
	}
	
	public void setConnectionStateListener(
			ConnectionStateListener connectionStateListener) {
		this.connectionStateListener = connectionStateListener;
	}

}
