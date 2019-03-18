package com.jd.seed.exercise.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.jd.seed.exercise.concurrent.forkJoin.Folder;
import com.jd.seed.exercise.concurrent.forkJoin.WordCounter;

/**
 * <pre>
 * ForkJoin
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午5:23:56
 */
public class ForkJoinTest {

	@Test
	public void forkJoin() throws FileNotFoundException, IOException {
		WordCounter counter = new WordCounter();
		Folder folder = Folder.loadFolderFromDirectory(new File("D:\\workspaces\\jd\\wms\\dev\\itms-jd"));
		String keywords = "mecarlen";
		long counts;
		long startTime;
        long stopTime;
		int repeatCount =3;
		long[] singleThreadTimes = new long[repeatCount];
        long[] forkedThreadTimes = new long[repeatCount];
        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = counter.countOccurrencesOnSingleThread(folder, keywords);
            stopTime = System.currentTimeMillis();
            singleThreadTimes[i] = (stopTime - startTime);
            System.out.println(counts + " , single thread search took " + singleThreadTimes[i] + "ms");
        }
		
        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = counter.countOccurrencesInParallel(folder, keywords);
            stopTime = System.currentTimeMillis();
            forkedThreadTimes[i] = (stopTime - startTime);
            System.out.println(counts + " , fork / join search took " + forkedThreadTimes[i] + "ms");
        }
        System.out.println("\nCSV Output:\n");
        System.out.println("Single thread,Fork/Join");        
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(singleThreadTimes[i] + "," + forkedThreadTimes[i]);
        }
        System.out.println();
		
	}
}
