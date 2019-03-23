package com.jd.seed.exercise.algorithm.other;

import java.util.Scanner;

import org.junit.Test;

import com.jd.seed.exercise.algorithm.sort.heap.HeapSort;

/**
 * <pre>
 * 二叉树
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月22日 下午4:21:12
 */
public class BinaryTreeNodeTest {

	@Test
	public void levelOrder() {
		HeapSort hs = new HeapSort();
		BinaryTreeNode.levelOrder(BinaryTreeNode.initNodesAndReturnRoot(hs.getNums()));
		BinaryTreeNode.beforeOrder(BinaryTreeNode.initNodesAndReturnRoot(hs.getNums()));
		BinaryTreeNode.midOrder(BinaryTreeNode.initNodesAndReturnRoot(hs.getNums()));
		BinaryTreeNode.afterOrder(BinaryTreeNode.initNodesAndReturnRoot(hs.getNums()));
	}
	/**
	 * <pre>
	 * x+y=x|y
	 * 
	 * </pre>
	 * 
	 * */
	@Test
	public void andAndBtyeOr() {
		Scanner scan = new Scanner(System.in);
		System.out.print("x=");
		int x =scan.nextInt();
		System.out.print("k=");
		int k =scan.nextInt();
		scan.close();
		System.out.println("y="+XandYtoXByteOr.and2Or(x, k));
	}
}
