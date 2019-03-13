package com.jd.seed.exercise.algorithm.sort.shell;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 希尔排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月11日 下午1:35:41
 */
public class ShellSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		int length = nums.length;
		int h = length / 2;
		while (h >= 1) {
			for (int i = 0; i < nums.length - h; i = i + h) {
				for (int j = i + h; j > 0; j = j - h) {
					if (nums[j] < nums[j - h]) {
						int temp = nums[j];
						nums[j] = nums[j - h];
						nums[j - h] = temp;
					}
				}
				print(h);
			}
			h = h / 2;
		}
	}

}
