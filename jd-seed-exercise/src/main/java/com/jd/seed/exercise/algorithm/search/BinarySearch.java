package com.jd.seed.exercise.algorithm.search;

/**
 * <pre>
 * 二分查找
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月11日 下午3:48:50
 */
public class BinarySearch {

	public int search(int[] nums, int target) {
		return search(nums, 0, nums.length - 1, target);
	}

	private int search(int[] nums, int low, int high, int target) {
		int mid = (low + high) / 2;
		if (low < high) {
			if (target == nums[mid]) {
				return mid;
			} else if (target < nums[mid]) {
				return search(nums, low, mid, target);
			} else {
				return search(nums, low + 1, high, target);
			}
		}
		return -1;
	}
}
