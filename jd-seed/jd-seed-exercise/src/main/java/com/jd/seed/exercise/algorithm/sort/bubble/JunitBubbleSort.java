package com.jd.seed.exercise.algorithm.sort.bubble;

import org.junit.Test;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 冒泡排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年2月25日 下午4:19:50
 */
public class JunitBubbleSort extends SortTemplate {
	
	@Test
	@Override
	public void execute() {
		super.execute();
	}

	@Override
	public void sort() {
		// 排序
		for (int firstlevel = 0; firstlevel < numCount - 1; ++firstlevel) {
			System.out.print("第" + (firstlevel + 1) + "轮：");
			for (int secondlevel = numCount - 1; secondlevel > firstlevel; --secondlevel) {
				// 交换
				if (nums[firstlevel] > nums[secondlevel]) {
					int tmp = nums[secondlevel];
					nums[secondlevel] = nums[firstlevel];
					nums[firstlevel] = tmp;
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
			System.out.print("->" + nums[firstlevel]);
			System.out.println();
		}
	}
}
