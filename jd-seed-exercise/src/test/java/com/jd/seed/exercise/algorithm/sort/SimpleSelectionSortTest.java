package com.jd.seed.exercise.algorithm.sort;

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
public class SimpleSelectionSortTest extends SortTemplate {
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
			int minIndex = firstlevel;
			for (int secondlevel = numCount - 1; secondlevel > firstlevel; --secondlevel) {
				// 交换
				if (nums[minIndex] > nums[secondlevel]) {
					minIndex= secondlevel;
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
			if(minIndex!=firstlevel){
				int tmp = nums[minIndex];
				nums[minIndex] = nums[firstlevel];
				nums[firstlevel] = tmp;
			}
			System.out.print("->" + nums[firstlevel]);
			System.out.println();
		}
	}
}
