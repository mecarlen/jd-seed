package com.jd.seed.exercise.algorithm.sort.selection;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 二元选择排序
 * 
 * 描述
 * 1、奇数排序正常
 * 2、偶数排序问题
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月8日 下午5:27:50
 */
public class TwoElementsSelectionSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		// 排序
		for (int firstlevel = 1; firstlevel <= nums.length / 2; firstlevel++) {
			int minIdx = firstlevel-1, maxIdx = nums.length-firstlevel;
			for (int secondlevel = firstlevel; secondlevel < nums.length - firstlevel; secondlevel++) {
				if (nums[secondlevel] < nums[minIdx]) {
					minIdx = secondlevel;
					continue;
				}
				if (nums[secondlevel] > nums[maxIdx]) {
					maxIdx = secondlevel;
				}
			}
			// 左边低位
			int tmp = nums[firstlevel - 1];
			nums[firstlevel - 1] = nums[minIdx];
			nums[minIdx] = tmp;
			// 右边高位
			tmp = nums[nums.length - firstlevel];
			nums[nums.length - firstlevel] = nums[maxIdx];
			nums[maxIdx] = tmp;
			System.out.println("minNum:"+nums[firstlevel-1]+",maxNum:"+nums[nums.length - firstlevel]);
			print(firstlevel);
		}
	}

	/**
	 * <pre>
	 * 排序内打印
	 * 
	 * </pre>
	 */
	void print(int times) {
		System.out.print("当前第 " + times + " 轮:  ");
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
