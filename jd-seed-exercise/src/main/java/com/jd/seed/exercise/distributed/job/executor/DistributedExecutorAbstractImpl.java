package com.jd.seed.exercise.distributed.job.executor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jd.seed.exercise.distributed.job.context.DistributedContext;
import com.jd.seed.util.date.DateConvertor;
import com.jd.seed.util.date.EnumDateFormat;

/**
 * <pre>
 * 分布式执行接口抽象实现类
 * 
 * </pre>
 * 
 * @author mecarlen 2015-4-29 下午3:23:33
 */
public abstract class DistributedExecutorAbstractImpl implements
		DistributedExecutor {
	/** 分布式work分组模式开关 */
//	final private static String DISTRIBUTED_GROUP_MODE_CACHE_KEY="";
	//分组模式下实例所属分组编号,从0开始
	protected int groupNum;
	//分组分几组
	protected int groupCount;
	//jimdb客户端
//	protected JdCacheUtils jdCacheUtils;
	//log
	private static final Log log = LogFactory.getLog(DistributedExecutorAbstractImpl.class);
	public boolean isGroupMode() {
//		Object obj=jdCacheUtils.getObject(DISTRIBUTED_GROUP_MODE_CACHE_KEY);
//		if(null==obj)
//			return false;
//		if(AirplanePaymentSwitchConstant.SWITCH_OPEN==(Integer)obj){
//			return true;
//		}
		return false;
	}

	public void postProcess(DistributedContext distributedContext) {
		this.groupNum=distributedContext.getGroupIndex();
    	this.groupCount=distributedContext.getGroups().size();
	}
	/**
	 * <pre>
	 * 独占直到任务执行完
	 * </pre>
	 * @param distributedContext DistributedContext
	 * */
	public void takeLeadership(DistributedContext distributedContext) {
    	int waitSeconds = Integer.MAX_VALUE;
    	try {
    		if (isGroupMode()) {
    			Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
    		}
    	} catch (InterruptedException ex) {
    		Thread.currentThread().interrupt();
    	}
	}

	public void notExecute(DistributedContext distributedContext) {
		
		log.info("-------* the member of "+distributedContext.getTargetName()+"Group "+distributedContext.getGroupIndex()+" " + getLocalId() +": i am not leader,so i do nothing *-------" + DateConvertor.date2Str(new Date(), EnumDateFormat.TIME_YMDHMS));
	}

	public String getLocalId() {
		try{
			return InetAddress.getLocalHost().getHostAddress();
		}catch(UnknownHostException ex){
			log.error("get host address failure on group mode",ex);
		}
		return "-1";
	}
	
//	public void setJdCacheUtils(JdCacheUtils jdCacheUtils){
//		this.jdCacheUtils = jdCacheUtils;
//	}
}
