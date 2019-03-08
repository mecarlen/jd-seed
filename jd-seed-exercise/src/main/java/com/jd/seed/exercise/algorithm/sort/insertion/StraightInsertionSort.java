package com.jd.seed.exercise.algorithm.sort.insertion;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 直接插入排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月7日 下午4:41:10
 */
public class StraightInsertionSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
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
