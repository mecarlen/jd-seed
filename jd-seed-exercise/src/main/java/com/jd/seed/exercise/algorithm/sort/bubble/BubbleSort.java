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
			for (int secondlevel = 0; secondlevel < numCount - firstlevel - 1; ++secondlevel) {
				if (nums[secondlevel] > nums[secondlevel + 1]) {
					int tmp = nums[secondlevel];
					nums[secondlevel] = nums[secondlevel + 1];
					nums[secondlevel + 1] = tmp;
				}
				print(firstlevel + 1, secondlevel + 1);
			}
			print(firstlevel + 1);
		}
	}

	void print(int firstlevel, int secondlevel) {
		System.out.print("  第 " + firstlevel + "-" + secondlevel + " 轮:  ");
		for (int index = 0; index < numCount; index++) {
			if (index == 0)
				System.out.print("[" + nums[index]);
			else if (index == (numCount - 1))
				System.out.print("," + nums[index] + "]");
			else
				System.out.print("," + nums[index]);
		}
		System.out.println();
	}

}
