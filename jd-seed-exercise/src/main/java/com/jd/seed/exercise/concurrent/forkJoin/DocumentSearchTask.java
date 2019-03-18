package com.jd.seed.exercise.concurrent.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * <pre>
 * 文档级查找
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午4:58:06
 */
@SuppressWarnings("serial")
public class DocumentSearchTask extends RecursiveTask<Long> {
	private Document searchDoc;
	private String keywords;
	
	public DocumentSearchTask(Document searchDoc, String keywords) {
		super();
		this.searchDoc = searchDoc;
		this.keywords = keywords;
	}
	
	@Override
	protected Long compute() {
		return WordCounter.occurrencesCount(searchDoc, keywords);
	}

}
