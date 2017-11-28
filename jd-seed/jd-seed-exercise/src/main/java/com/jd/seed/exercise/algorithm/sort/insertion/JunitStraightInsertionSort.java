package com.jd.seed.exercise.algorithm.sort.insertion;

import org.junit.Test;

import com.jd.seed.exercise.algorithm.sort.SortTemplate;

/**
 * <pre>
 * 直接插入排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 下午7:52:18
 */
public class JunitStraightInsertionSort extends SortTemplate {

	@Test
	public void execute() {
		super.execute();
	}

	@Override
	public void sort() {
		for (int firstlevel = 1; firstlevel < numCount; firstlevel++) {
			System.out.print("第" + firstlevel + "轮：");
			if (nums[firstlevel] < nums[firstlevel - 1]) {
				int secondlevel = firstlevel - 1;
				int tmp = nums[firstlevel];
				nums[firstlevel] = nums[firstlevel - 1];
				while (secondlevel > 0 && nums[secondlevel] > tmp) {
					nums[secondlevel + 1] = nums[secondlevel];
					secondlevel--;
				}
				nums[secondlevel + 1] = tmp;
			}
			for (int i = 0; i <= firstlevel; i++) {
				if (i == 0 && i == firstlevel)
					System.out.print("[" + nums[i] + "]");
				else if (i == 0)
					System.out.print("[" + nums[i]);
				else if (i == firstlevel)
					System.out.print("," + nums[i] + "]");
				else
					System.out.print("," + nums[i]);
			}
			System.out.println();
		}
	}

}
