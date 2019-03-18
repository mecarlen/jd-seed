package com.jd.seed.exercise.concurrent.forkJoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * <pre>
 * 文件夹级查找
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午5:01:59
 */
@SuppressWarnings("serial")
public class FolderSearchTask extends RecursiveTask<Long> {
	private Folder searchFolder;
	private String keywords;

	public FolderSearchTask(Folder searchFolder, String keywords) {
		super();
		this.searchFolder = searchFolder;
		this.keywords = keywords;
	}

	@Override
	protected Long compute() {
		Long count = 0L;
		List<RecursiveTask<Long>> forks = new LinkedList<>();
		for (Folder subfolder : searchFolder.getSubfolders()) {
			FolderSearchTask task = new FolderSearchTask(subfolder, keywords);
			forks.add(task);
			task.fork();
		}

		for (Document document : searchFolder.getDocuments()) {
			DocumentSearchTask task = new DocumentSearchTask(document, keywords);
			forks.add(task);
			task.fork();
		}

		for (RecursiveTask<Long> task : forks) {
			count = count + task.join();
		}
		return count;
	}

}
