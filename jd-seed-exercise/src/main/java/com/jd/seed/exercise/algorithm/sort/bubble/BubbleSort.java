package com.jd.seed.exercise.algorithm.sort.bubble;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 冒泡排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月7日 下午4:12:28
 */
public class BubbleSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		// 排序
		for (int firstlevel = 0; firstlevel < numCount - 1; ++firstlevel) {
			System.out.print("第" + (firstlevel + 1) + "轮：");
			for (int secondlevel = 0; secondlevel < numCount - firstlevel - 1; ++secondlevel) {
				if (nums[secondlevel] > nums[secondlevel + 1]) {
					int tmp = nums[secondlevel];
					nums[secondlevel] = nums[secondlevel + 1];
					nums[secondlevel + 1] = tmp;
				}
				if (secondlevel == 0 && 1 == numCount - firstlevel - 1)
					System.out.print("[" + nums[secondlevel] + "]");
				else if (secondlevel == 0)
					System.out.print("[" + nums[secondlevel]);
				else if (secondlevel == numCount - firstlevel - 2)
					System.out.print("," + nums[secondlevel] + "]");
				else
					System.out.print("," + nums[secondlevel]);
			}
			System.out.print("->" + nums[numCount - firstlevel - 1]);
			System.out.println();
		}
	}

}
