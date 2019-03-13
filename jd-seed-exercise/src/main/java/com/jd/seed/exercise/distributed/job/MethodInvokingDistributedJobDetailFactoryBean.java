package com.jd.seed.exercise.distributed.job;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.util.Assert;

import com.jd.seed.exercise.distributed.job.context.DistributedContext;
import com.jd.seed.exercise.distributed.job.context.Group;
import com.jd.seed.exercise.distributed.job.executor.DistributedExecutor;

/**
 * <pre>
 *  分布式MethodInvokingJobDetailFactoryBean扩展实现
 *  
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:45:03
 */
public class MethodInvokingDistributedJobDetailFactoryBean  extends MethodInvokingJobDetailFactoryBean implements DisposableBean, DistributedControl {
	//logger
	private static final Logger DISTRIBUTED_JOB_LOGGER = LoggerFactory.getLogger(MethodInvokingDistributedJobDetailFactoryBean.class);
	//任务名称
	private String targetName;
	//任务说明
    private String description;
    //任务组列表
    private List<Group> groups;
    //实例唯一标示
    private String localId;
    //单机模下任务组
    private Group singleModelGroup;
    //实例所属任务组的编号
    private int groupIndex;
    //ZooKeeper客户端工厂
    private ZooKeeperClientFactory zooKeeperClientFactory;
    //具体业务执行者
    private DistributedExecutor distributedExecutor;
    //分布式场景
    private DistributedContext distributedContext;
    //分布式调度者
    private DistributedDispatcher distributedDispatcher;
    
    /**
     * <pre>
     * 
     * </pre>
     * */
    @Override
    protected void postProcessJobDetail(JobDetail jobDetail) {
        Object targetObject = getTargetObject();
        //分布式任务必须实现DistributedExecutor接口，否则跳过
        if (targetObject instanceof DistributedExecutor) {
            DistributedExecutor distributedExecutor = (DistributedExecutor) targetObject;

            Assert.state(this.zooKeeperClientFactory != null, "zooKeeperClientFactory must be set when using 'zooKeeperClientFactory'");
            Assert.notEmpty(this.groups, "groups must be set when using 'groups'");

            this.distributedExecutor = distributedExecutor;
            this.localId = distributedExecutor.getLocalId();
            this.groupIndex = queryGroupIndex();
         
            if (groupIndex > -1) {
                distributedContext = new DistributedContext(this);
                distributedDispatcher = new DistributedDispatcher(zooKeeperClientFactory, distributedContext);
                distributedExecutor.postProcess(distributedContext);
                //Cannot be started more than once
                if(CuratorFrameworkState.LATENT==zooKeeperClientFactory.getCuratorFramework().getState()){
                	zooKeeperClientFactory.getCuratorFramework().start();
                }
            }
        }
    }
    
    /**
     * <pre>
     * 遍历任务分组列表,根据实例唯一标示取其所属组
     * 
     * </pre>
     * @return int
     * */
    private int queryGroupIndex() {
        int index = 0;
        for (Group group : groups) {
            for (String id : group.getIdlist()) {
                if (id.equals(localId)) {
                    return index;
                }
            }
            index++;
        }
        return -1;
    }
    /**
     * <pre>
     * 取任务名称
     * 
     * 描述:
     *   默认取任务bean名称
     * </pre>
     * @return String
     * */
    public String getTargetName() {
        if (targetName == null) {
            targetName = getJobDetailName();
        }
        return targetName;
    }

    public String getDescription() {
        return description;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getLocalId() {
        return localId;
    }

    public int getGroupIndex() {
        return groupIndex;
    }
    
    private String getJobDetailName() {
        JobDetail jobDetail = (JobDetail) getObject();
        return jobDetail.getKey().getName();
    }
    /**
     * <pre>
     * 
     * </pre>
     * */
    @Override
    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        if (distributedDispatcher != null) {
            if (distributedExecutor.isGroupMode()) {
                //分组运行模式需要验证leader
                if(!distributedDispatcher.isLeader()){
                	distributedDispatcher.take();
                }
                Object obj=null;
                if (distributedDispatcher.hasLeadership()) {
                	obj=super.invoke();
                	distributedDispatcher.release();
                } else {
                    //分组机器启动分组模式不是leader则执行notExecute方法
                    distributedExecutor.notExecute(distributedContext);
                }
                return obj;
            } else {
            	if(getSingleModelGroupServerList().contains(distributedExecutor.getLocalId())){
	                //单机模式则指定机器才可执行业务逻辑
	                return super.invoke();
            	} else {
            		distributedExecutor.notExecute(distributedContext);
            		return null;
            	}
            }
        } else {
        	DISTRIBUTED_JOB_LOGGER.info("job run on single model");
        	if(!distributedExecutor.isGroupMode()&&getSingleModelGroupServerList().contains(distributedExecutor.getLocalId())){
                //单机模式则指定机器才可执行业务逻辑
                return super.invoke();
        	} else {
        		//当实例唯一标示(IP)不在组内时,distributedContext未初始化
        		if(null!=distributedContext){
        			distributedExecutor.notExecute(distributedContext);
        		}
        		return null;
        	}
        }
    }
    /**
     * <pre>
     * 取当前单机执行分组服务器列表
     * 
     * </pre>
     * @return List<String>
     * */
    private List<String> getSingleModelGroupServerList(){
//    	List<String> singleModelGroupServerList=new ArrayList<String>();
//    	try{
//    		String singleModelGroupServerip=GenericConfiger.getConfigContent(PopConfigurationEnum.DISTRIBUTED_WORKER_SINGLE_MODEL_SERVERIP);
//    		if(StringUtils.isNotBlank(singleModelGroupServerip)){
//    			singleModelGroupServerList.add(singleModelGroupServerip);
//    			return singleModelGroupServerList;
//    		}
//    	}catch(Exception ex){
//    		LOGGER.error("取"+PopConfigurationEnum.DISTRIBUTED_WORKER_SINGLE_MODEL_SERVERIP.getDesc()+"异常", ex);
//    	}
    	
    	return this.singleModelGroup.getIdlist();
    }
    
    public void destroy() throws Exception {
        if (distributedDispatcher != null) {
            distributedDispatcher.close();
        }
    }
    public void setZooKeeperClientFactory(ZooKeeperClientFactory zooKeeperClientFactory) {
        this.zooKeeperClientFactory = zooKeeperClientFactory;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DistributedDispatcher getDistributedDispatcher() {
        return distributedDispatcher;
    }

    public DistributedExecutor getDistributedExecutor() {
        return distributedExecutor;
    }
	public void setSingleModelGroup(Group singleModelGroup) {
		this.singleModelGroup = singleModelGroup;
	}
}
