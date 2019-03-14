package com.jd.seed.exercise.algorithm.search;

import org.junit.Test;
import org.springframework.util.Assert;

import com.jd.seed.exercise.algorithm.sort.quick.QuickSort;

/**
 * <pre>
 * 查找算法
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月11日 下午4:00:42
 */
public class SearchTest {

	@Test
	public void binarySearch() {
		// 快排取一个有序数组
		QuickSort qs = new QuickSort();
		int[] nums = qs.getSortedArray();
		int target = 3;
		// 二分查找查找目标数下标
		BinarySearch bs = new BinarySearch();
		int tgIdx = bs.search(nums, target);
		Assert.isTrue(tgIdx > 0 && target == nums[tgIdx], "查找失败");
	}
	
	@Test
	public void bitOpt() {
		System.out.println(10 << 1);
	}
	
}
