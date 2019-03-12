package com.jd.seed.exercise.distributed.job;

import java.io.Closeable;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;

import com.jd.seed.exercise.distributed.job.context.DistributedContext;
import com.jd.seed.exercise.distributed.job.executor.DistributedExecutor;

/**
 * <pre>
 * 分布式调度者
 * 
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:24:38
 */
public class DistributedDispatcher extends LeaderSelectorListenerAdapter implements Closeable {
	// logger
//	private static final Logger LOGGER = Logger.getLogger(DistributedDispatcher.class);
	private String leaderPath;
	final private Object lock = new Object();
	private boolean leader = false;
	private ZooKeeperClientFactory zooKeeperClientFactory;
	private volatile LeaderSelector leaderSelector;
	private DistributedContext distributedContext;
	private DistributedExecutor distributedExecutor;

	public DistributedDispatcher(ZooKeeperClientFactory zooKeeperClientFactory, DistributedContext distributedContext) {
		this.zooKeeperClientFactory = zooKeeperClientFactory;
		this.distributedContext = distributedContext;
		this.distributedExecutor = distributedContext.getDistributedExecutor();
		this.leaderPath = buildLeaderPath();
		zooKeeperClientFactory.getCuratorFramework().getConnectionStateListenable().addListener(this);
		leaderSelector = new LeaderSelector(zooKeeperClientFactory.getCuratorFramework(), leaderPath, this);
		leaderSelector.setId(distributedExecutor.getLocalId());
		leaderSelector.autoRequeue();
		leaderSelector.start();
	}

	private String buildLeaderPath() {
		StringBuilder stringBuilder = new StringBuilder();
		String rootPath = zooKeeperClientFactory.getZooKeeperConfig().getRootPath();
		stringBuilder.append(rootPath);
		if (!rootPath.endsWith("/")) {
			stringBuilder.append("/");
		}
		stringBuilder.append(distributedContext.getTargetName());
		stringBuilder.append("/");
		stringBuilder.append(distributedContext.getGroupIndex());
		return stringBuilder.toString();
	}

	public void start() {
		leaderSelector.start();
	}

	public boolean isLeader() {
		return leader;
	}

	public void release() {
		// 释放leader角色
		leader = false;
		// takeLeadership方法将会中断并返回.
		leaderSelector.interruptLeadership();
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	// 重新获取leader角色--选举
	public void take() {
		leaderSelector.requeue();
	}

	public void close() {
		leader = false;
		leaderSelector.close();
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public boolean hasLeadership() {
		return leaderSelector.hasLeadership();
	}

	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		// 如果takeLeadership方法被调用,说明此selector实例已经为leader
		// 此方法需要阻塞,直到selector放弃leader角色
		leader = true;
		while (leader) {
			synchronized (lock) {
				lock.wait();
			}
		}
	}

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		// 对于LeaderSelector,底层实现为对leaderPath节点使用了"排他锁",
		// "排他锁"的本质,就是一个"临时节点"
		// 如果接收到LOST,说明此selector实例已经丢失了leader信息.
		if (newState == ConnectionState.SUSPENDED || newState == ConnectionState.LOST) {
			leader = false;
			synchronized (lock) {
				lock.notifyAll();
			}
		}
	}

	public DistributedExecutor getDistributedExecutor() {
		return distributedExecutor;
	}

	public ZooKeeperClientFactory getZooKeeperClientFactory() {
		return zooKeeperClientFactory;
	}
}
