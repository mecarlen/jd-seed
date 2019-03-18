package com.jd.seed.exercise.concurrent.forkJoin;
/**
 * <pre>
 * 关键字查找
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午5:11:28
 */

import java.util.concurrent.ForkJoinPool;

public class WordCounter {
	final private ForkJoinPool forkJoinPool = new ForkJoinPool();
	
	static String[] wordsIn(String line) {
		return line.trim().split("[\\s|\\p{Punct}]+");
	}
	
	static Long occurrencesCount(Document document, String keywords) {
        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (keywords.equalsIgnoreCase(word)) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
	
	public Long countOccurrencesOnSingleThread(Folder folder, String keywords) {
        long count = 0;
        for (Folder subFolder : folder.getSubfolders()) {
            count = count + countOccurrencesOnSingleThread(subFolder, keywords);
        }
        for (Document document : folder.getDocuments()) {
            count = count + occurrencesCount(document, keywords);
        }
        return count;
    }
	
	public Long countOccurrencesInParallel(Folder folder, String keywords) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, keywords));
    }
}
