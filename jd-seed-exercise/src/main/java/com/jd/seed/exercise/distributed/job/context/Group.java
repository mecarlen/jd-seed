package com.jd.seed.exercise.distributed.job.context;
import java.util.List;

/**
 * <pre>
 *  分布式分组组类
 *  
 * </pre>
 * 
 * @author mecarlen 2015-4-23 下午5:27:20
 */
public class Group {
	//组名
	private String name;
	//组内成员唯一标示列表
    private List<String> idlist;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getIdlist() {
		return idlist;
	}
	public void setIdlist(List<String> idlist) {
		this.idlist = idlist;
	}
    
}
