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
		for (int firstlevel = 0; firstlevel < nums.length - 1; ++firstlevel) {
			int minIdx = selectMinIdx(nums, firstlevel);
			if(minIdx!=firstlevel) {
				int tmp = nums[firstlevel];
				nums[firstlevel] = nums[minIdx];
				nums[minIdx] = tmp;
			}
			print(firstlevel+1);
		}

	}

	/**
	 * <pre>
	 * 选择值最小的下标
	 * 
	 * </pre>
	 * 
	 */
	int selectMinIdx(int[] nums, int firstlevel) {
		int minIdx = firstlevel;
		for (int secondlevel = minIdx + 1; secondlevel < nums.length; ++secondlevel) {
			if (nums[minIdx] > nums[secondlevel]) {
				minIdx = secondlevel;
			}
		}
		return minIdx;
	}

}
