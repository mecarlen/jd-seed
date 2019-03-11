package com.jd.seed.exercise.algorithm.sort.heap;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 堆排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月11日 上午10:12:20
 */
public class HeapSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		for (int firstlevel = nums.length - 1; firstlevel > 0; firstlevel--) {
			max_heapify(nums, firstlevel);
			// 堆顶元素(第一个元素)与Kn交换
			int temp = nums[0];
			nums[0] = nums[firstlevel];
			nums[firstlevel] = temp;
			print(nums.length-firstlevel);
		}

	}

	/***
	 *
	 * 将数组堆化 firstlevel = 第一个非叶子节点。 从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
	 * 叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
	 *
	 * @param nums int[]
	 * @param firstlevel int 
	 */
	public void max_heapify(int[] nums, int firstlevel) {
		int child;
		for (int secondlevel = (firstlevel - 1) / 2; secondlevel >= 0; secondlevel--) {
			// 左子节点位置
			child = 2 * secondlevel + 1;
			// 右子节点存在且大于左子节点，child变成右子节点
			if (child != firstlevel && nums[child] < nums[child + 1]) {
				child++;
			}
			// 交换父节点与左右子节点中的最大值
			if (nums[secondlevel] < nums[child]) {
				int temp = nums[secondlevel];
				nums[secondlevel] = nums[child];
				nums[child] = temp;
			}
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
