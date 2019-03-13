package com.jd.seed.exercise.distributed.job.executor;

import com.jd.seed.exercise.distributed.job.context.DistributedContext;

/**
 * <pre>
 * 分布式执行接口
 * 
 * 描述:
 *   业务类需要实现此接口
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:31:16
 */
public interface DistributedExecutor {
	/**
	 * <pre>
	 * 获取当前分布式work是否是分组运行
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	boolean isGroupMode();

	/**
	 * <pre>
	 * 获取本地唯一标示
	 * 
	 * 描述:
	 *   推荐使用本机ip
	 * </pre>
	 * 
	 * @return
	 */
	String getLocalId();

	/**
	 * <pre>
	 * 分布式机器初始化完成后的后处理扩展方法
	 * 
	 * </pre>
	 * 
	 * @param distributedContext
	 */
	void postProcess(final DistributedContext distributedContext);

	/**
	 * <pre>
	 * 获取leader之后的回调方法
	 * 
	 * 描述:
	 *  获取leader权限后,独占leader权限worker运行周期时间
     *  1、解决leader的频繁选举,减少leader选举开销
     *  2、保障leader可以公平的在组内成员之间切换
     *  3、业务执行时间超过worker运行周期，worker数据在组内重复执行问题？
     *    解决方案:1)取出worker数据先更新其状态,
     *          2)处理异常时还原其状态,
     *          3)配置concurrent=false保障worker不可并行,保障以上三点保障组内数据不会重得执行
	 * </pre>
	 */
	void takeLeadership(final DistributedContext distributedContext);

	/**
	 * <pre>
	 * 分布式分组运行模式下，如果该机器没有权限执行业务逻辑，会调用此方法
	 * 
	 * </pre>
	 */
	void notExecute(final DistributedContext distributedContext);
}
