package com.jd.seed.exercise.distributed.job;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.jd.seed.exercise.distributed.job.context.Group;
import com.jd.seed.util.http.IpUtils;

/**
 * <pre>
 * 单机MethodInvokingJobDetailFactoryBean、
 * 
 * 描述
 * 实现worker在指定服务器自动运行
 * 
 * </pre>
 * 
 * @author mecarlen 2017年5月26日 上午10:22:31
 */
public class MethodInvokingSingleJobDetailFactoryBean extends MethodInvokingJobDetailFactoryBean {
	//logger
	private static final Logger SINGLE_WORKER_LOGGER = Logger.getLogger(MethodInvokingSingleJobDetailFactoryBean.class);
	//默认执行者
	private Group commonWorkDefaultExecutorGroup;
	@Override
	public Object invoke() throws InvocationTargetException, IllegalAccessException {
		if(isExecutor()){
			return super.invoke();
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 判断本机是否有权执行worker
	 * 
	 * </pre>
	 * 
	 * @return boolean
	 * */
	private boolean isExecutor(){
		//通过配置文件或统一配置中心获取
		String currExecutorIp="127.0.0.1";
		//配置未取到取默认执行者ip
		String currentWorkerInfo="执行worker->"+this.getTargetObject()+"."+ this.getTargetMethod()+"时,";
		if(StringUtils.isBlank(currExecutorIp)){
			currExecutorIp=this.commonWorkDefaultExecutorGroup.getIdlist().get(0);
			SINGLE_WORKER_LOGGER.info(currentWorkerInfo +"获取配置中心执行者IP异常取默认执行者IP" + currExecutorIp);
		}
		if(currExecutorIp.equals(IpUtils.getLocalIp())){
			return true;
		} else {
			SINGLE_WORKER_LOGGER.warn(currentWorkerInfo + "当前服务器不是指定的执行者" + currExecutorIp);
		}
		return false;
	}
	

	public void setCommonWorkDefaultExecutorGroup(Group commonWorkDefaultExecutorGroup) {
		this.commonWorkDefaultExecutorGroup = commonWorkDefaultExecutorGroup;
	}
}
