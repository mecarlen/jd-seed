package com.jd.seed.exercise.algorithm.sort;

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
public class StraightInsertionSortTest extends SortTemplate {

	@Test
	public void execute() {
		super.execute();
	}

	@Override
	public void sort() {
		for (int firstlevel = 1; firstlevel < numCount; firstlevel++) {
			System.out.print("第" + (firstlevel) + "轮：");
			int tmp = nums[firstlevel];
			for (int secondlevel = firstlevel - 1; secondlevel >= 0; secondlevel--) {
				if (tmp < nums[secondlevel]) {
					nums[secondlevel + 1] = nums[secondlevel];
					nums[secondlevel] = tmp;
				}
				if (secondlevel == firstlevel - 1)
					System.out.print("[" + nums[secondlevel + 1]);
				else
					System.out.print("," + nums[secondlevel + 1]);
			}
			System.out.print("," + nums[0] + "]");
			System.out.println();
		}
	}

}
