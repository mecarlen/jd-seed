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
	protected int numCount = 21;

	private void init() {
		// 随机准备数据
		Random rd = new Random();
		nums = new int[numCount];
		System.out.println("============"+this.getClass().getSimpleName()+"=============");
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
		sort(this.nums);
		print();
	}

	public abstract void sort(int[] nums);
	
	/**
	 * <pre>
	 * 排序内打印
	 * 
	 * </pre>
	 * @param times int 轮次
	 */
	protected void print(int times) {
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
	
	public int[] getSortedArray() {
		execute();
		return nums;
	}
}
