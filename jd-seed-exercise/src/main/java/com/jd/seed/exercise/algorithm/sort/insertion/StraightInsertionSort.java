package com.jd.seed.exercise.algorithm.sort.insertion;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 直接插入排序
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月7日 下午4:41:10
 */
public class StraightInsertionSort extends SortTemplate {

	@Override
	public void sort(int[] nums) {
		for (int firstlevel = 1; firstlevel < nums.length; firstlevel++) {
			if (nums[firstlevel] < nums[firstlevel - 1]) {
				//当前位置小于左边第一个数时，先将左一右移到当前位置
				int tmp = nums[firstlevel];
				nums[firstlevel] = nums[firstlevel - 1];
				int secondlevel = firstlevel - 1;
				//然后再比较，如果发现标记位值大于左边的值，直接插入
				//实现：从当前位置左边第二个数与当前值比较，比当前值大于的都右移，否则直接插入
				while (secondlevel > 0 && tmp < nums[secondlevel - 1]) {
					nums[secondlevel] = nums[secondlevel - 1];
					secondlevel--;
				}
				nums[secondlevel] = tmp;
			}
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
