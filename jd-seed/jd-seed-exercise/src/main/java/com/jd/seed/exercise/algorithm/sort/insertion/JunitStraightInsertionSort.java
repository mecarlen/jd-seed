package com.jd.seed.exercise.algorithm.sort.insertion;

import org.junit.Test;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 直接插入排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 下午7:52:18
 */
public class JunitStraightInsertionSort extends SortTemplate {

	@Test
	public void execute() {
		super.execute();
	}

	@Override
	public void sort() {
		for (int firstlevel = 1; firstlevel < numCount; firstlevel++) {
			if (nums[firstlevel] < nums[firstlevel - 1]) {
				int secondlevel = firstlevel - 1;
				int tmp = nums[firstlevel];
				nums[firstlevel] = nums[firstlevel - 1];
				while (secondlevel < nums[secondlevel]) {
					nums[secondlevel + 1] = nums[secondlevel];
					secondlevel--;
				}
				nums[secondlevel + 1] = tmp;
			}
		}
	}

}
