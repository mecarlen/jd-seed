package com.jd.seed.exercise.distributed.job;

import java.util.List;

import com.jd.seed.exercise.distributed.job.context.Group;
import com.jd.seed.exercise.distributed.job.executor.DistributedExecutor;

/**
 * <pre>
 * 分布式控制器
 * 
 * 描述:
 * 	协调控制分布式初始化、执行
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:30:47
 */
public interface DistributedControl {
	/**
	 * <pre>
	 * 取分布式任务执行者
	 * </pre>
	 * @return DistributedExecutor
	 * */
	DistributedExecutor getDistributedExecutor();
	/**
	 * <pre>
	 * 取分布式任务名称
	 * 
	 * </pre>
	 * @return String
	 * */
	String getTargetName();
	/**
	 * <pre>
	 * 取分布式任务说明
	 * 
	 * </pre>
	 * @return String
	 * */
	String getDescription();
	/**
	 * <pre>
	 * 取任务分组列表
	 * 
	 * </pre>
	 * @return List<Group>
	 * */
	List<Group> getGroups();
	/**
	 * <pre>
	 * 取任务实例唯标示
	 * 
	 * </pre>
	 * @return String
	 * */
	String getLocalId();
	/**
	 * <pre>
	 * 取任务实例所属组编号
	 * 
	 * </pre>
	 * @return int
	 * */
	int getGroupIndex();
}
