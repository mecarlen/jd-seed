package com.jd.seed.exercise.algorithm.sort.quick;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 快速排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月8日 下午2:40:48
 */
public class QuickSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		int times = 0;
		sort(nums, 0, nums.length - 1, times);
	}

	/**
	 * <pre>
	 *  默认升序排序
	 * 
	 * </pre>
	 * 
	 * @param nums
	 *            int[]
	 * @param low
	 *            int 低位坐标
	 * @param high
	 *            int 高位坐标
	 * @param times
	 *            int 比较轮数
	 */
	public void sort(int[] nums, int low, int high, int times) {
		if (low < high) {
			++times;
			// 将待排序数组一分为二，得到中间位置下标
			int partionIdx = partion(nums, low, high, times);
			// 为partionIdx左边数据
			sort(nums, low, partionIdx - 1, times);
			// 为partionIdx右边数据
			sort(nums, partionIdx + 1, high, times);
		}
	}

	/**
	 * <pre>
	 * 确认二分座标
	 * 
	 * </pre>
	 * 
	 * @param nums
	 *            int[]
	 * @param low
	 *            int 低位坐标
	 * @param high
	 *            int 高位坐标
	 * @param times
	 *            int 执行轮数
	 * @return int
	 */
	private int partion(int[] nums, int low, int high, int times) {
		// 初始分二坐标为最低坐标值
		int partionVal = nums[low];
		while (low < high) {
			// 从高位向低位比较
			while (low < high && nums[high] >= partionVal)
				--high;
			// 将高位小于哨兵位的值与哨兵位交换
			swap(nums, low, high);
			print(times, low);
			// 从低位向高化位比较
			while (low < high && nums[low] <= partionVal)
				++low;
			swap(nums, low, high);
			print(times, low);
		}
		print(times, low);
		return low;
	}

	/**
	 * <pre>
	 *  数据互换
	 *  将sourIdx和targetIdx位置数值互换
	 * 
	 * </pre>
	 * 
	 * @param nums
	 *            int[]
	 * @param sourceIdx
	 *            int 源数据下标
	 * @param targetIdx
	 *            int 目标位下标
	 */
	private void swap(int[] nums, int sourceIdx, int targetIdx) {
		int tmp = nums[sourceIdx];
		nums[sourceIdx] = nums[targetIdx];
		nums[targetIdx] = tmp;
	}

	/**
	 * <pre>
	 * 排序内打印
	 * 
	 * </pre>
	 */
	void print(int times, int partionIdx) {
		System.out.print("当前第 " + times + " 轮:  " + partionIdx + ":" + nums[partionIdx]);
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
