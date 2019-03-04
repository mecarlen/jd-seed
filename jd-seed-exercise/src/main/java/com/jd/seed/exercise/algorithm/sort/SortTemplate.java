package com.jd.seed.exercise.algorithm.sort;

import java.util.Random;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 下午7:55:09
 */
public abstract class SortTemplate {
	protected int[] nums;
	protected int numCount = 10;

	private void init() {
		// 随机准备数据
		Random rd = new Random();
		nums = new int[numCount];
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
	}

	public void execute() {
		init();
		sort();
		print();
	}

	public abstract void sort();

	private void print() {
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
