package com.jd.seed.exercise.distributed.job.context;
import java.util.List;

import com.jd.seed.exercise.distributed.job.DistributedControl;
import com.jd.seed.exercise.distributed.job.executor.DistributedExecutor;

/**
 * <pre>
 * 分布式场景
 * 描述:
 *   保存每个具体任务上下文
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:25:52
 */
public class DistributedContext {
	//任务名称
	private final String targetName;
	//任务说明
	private final String description;
	//任务分组列表
	private final List<Group> groups;
	//任务实例唯一标示
	private final String localId;
	//任务实例所属组编号
	private final int groupIndex;
	//任务实例所属组
	private final Group group;
	//分布式控制器
	private DistributedControl distributedControl;
	//任务具体执行者
	private DistributedExecutor distributedExecutor;

	public DistributedContext(DistributedControl distributedControl) {
		this.distributedControl = distributedControl;
		this.distributedExecutor = distributedControl.getDistributedExecutor();
		this.targetName = distributedControl.getTargetName();
		this.description = distributedControl.getDescription();
		this.localId = distributedControl.getLocalId();
		this.groups = distributedControl.getGroups();
		this.groupIndex = distributedControl.getGroupIndex();
		this.group = groups.get(groupIndex);
	}

	public String getTargetName() {
		return targetName;
	}

	public String getDescription() {
		return description;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public Group getGroup() {
		return group;
	}

	public String getLocalId() {
		return localId;
	}
	public DistributedControl getDistributedControl() {
        return distributedControl;
    }

    public DistributedExecutor getDistributedExecutor() {
        return distributedExecutor;
    }
}