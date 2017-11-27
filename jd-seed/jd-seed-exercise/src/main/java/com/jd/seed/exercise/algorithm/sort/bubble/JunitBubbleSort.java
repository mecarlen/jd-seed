package com.jd.seed.exercise.algorithm.sort.bubble;

import java.util.Random;

/**
 * <pre>
 * 冒泡排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年2月25日 下午4:19:50
 */
public class JunitBubbleSort {
	
	public void sort(){
		//随机准备数据
		Random rd=new Random();
		int numCount=10;
		int[] nums=new int[numCount];
		System.out.print("初始：");
		for(int index=0;index<numCount;index++){
			nums[index]=rd.nextInt(100);
			if(index==0)
				System.out.print("["+nums[index]);
			else if(index==(numCount-1))
				System.out.print(","+nums[index]+"]");
			else
				System.out.print(","+nums[index]);
		}
		System.out.println("---------------------------------------------");
		//排序
		for(int firstlevel=0;firstlevel<numCount-1;++firstlevel){
//			System.out.print("第 "+(firstlevel+1)+" 轮：");
			for(int secondlevel=0;secondlevel<numCount-firstlevel-1;++secondlevel){
				//交换
				if(nums[firstlevel]<nums[secondlevel]){
					int tmp=nums[secondlevel];
					nums[secondlevel]=nums[firstlevel];
					nums[firstlevel]=tmp;
				}
				//展示
//				if(secondlevel==0)
//					System.out.print("["+nums[secondlevel]);
//				else if(secondlevel==(numCount-1))
//					System.out.print(","+nums[secondlevel]+"]");
//				else
//					System.out.print(","+nums[secondlevel]);
			}
//			System.out.println();
		}
		
		System.out.print("结束：");
		for(int index=0;index<numCount;index++){
			nums[index]=rd.nextInt(100);
			if(index==0)
				System.out.print("["+nums[index]);
			else if(index==(numCount-1))
				System.out.print(","+nums[index]+"]");
			else
				System.out.print(","+nums[index]);
		}
	}
}
