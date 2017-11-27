package com.jd.seed.exercise.algorithm.sort.bubble;

import java.util.Random;

import org.junit.Test;

/**
 * <pre>
 * 冒泡排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年2月25日 下午4:19:50
 */
public class JunitBubbleSort {
	@Test
	public void sort() {
		// 随机准备数据
		Random rd = new Random();
		int numCount = 20;
		int[] nums = new int[numCount];

		System.out.print("初始：");
		for (int index = 0; index < numCount; index++) {
			nums[index] = rd.nextInt(100);
			if (index == 0)
				System.out.print("[" + nums[index]);
			else if (index == (numCount - 1))
				System.out.print("," + nums[index] + "]");
			else
				System.out.print("," + nums[index]);
		}
		System.out.println();
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

		System.out.print("结束：");
		for (int index = 0; index < numCount; index++) {
			// nums[index]=rd.nextInt(100);
			if (index == 0)
				System.out.print("[" + nums[index]);
			else if (index == (numCount - 1))
				System.out.print("," + nums[index] + "]");
			else
				System.out.print("," + nums[index]);
		}
	}
}
