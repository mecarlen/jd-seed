package com.jd.seed.exercise.algorithm.sort.selection;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 简单选择
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月7日 下午4:43:54
 */
public class SimpleSelectionSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		// 排序
		for (int firstlevel = 0; firstlevel < numCount - 1; ++firstlevel) {
			System.out.print("第" + (firstlevel + 1) + "轮：");
			int minIndex = firstlevel;
			for (int secondlevel = numCount - 1; secondlevel > firstlevel; --secondlevel) {
				// 交换
				if (nums[minIndex] > nums[secondlevel]) {
					minIndex = secondlevel;
				}
				// 展示
				if (secondlevel == numCount - 1 && secondlevel == firstlevel + 1)
					System.out.print("[" + nums[secondlevel] + "]");
				else if (secondlevel == numCount - 1)
					System.out.print("[" + nums[secondlevel]);
				else if (secondlevel == firstlevel + 1)
					System.out.print("," + nums[secondlevel] + "]");
				else
					System.out.print("," + nums[secondlevel]);
			}
			if (minIndex != firstlevel) {
				int tmp = nums[minIndex];
				nums[minIndex] = nums[firstlevel];
				nums[firstlevel] = tmp;
			}
			System.out.print("->" + nums[firstlevel]);
			System.out.println();
		}
	}

}
