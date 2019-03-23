package com.jd.seed.exercise.algorithm.other;

import java.util.LinkedList;
import java.util.Stack;

/**
 * <pre>
 * 二叉树节点
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月22日 下午3:04:52
 */
public class BinaryTreeNode {
	int val;
	BinaryTreeNode left;
	BinaryTreeNode right;

	BinaryTreeNode(int val) {
		this.val = val;
	}

	/**
	 * <pre>
	 * 层次初始化
	 * 
	 * </pre>
	 * 
	 * @param nums
	 *            int[]
	 * @return BinaryTreeNode
	 */
	public static BinaryTreeNode initNodesAndReturnRoot(int[] nums) {
		BinaryTreeNode[] nodes = new BinaryTreeNode[nums.length];
		for (int i = 0; i < nums.length; i++) {
			nodes[i] = new BinaryTreeNode(nums[i]);
		}

		for (int i = 0; i < nodes.length / 2; i++) {
			nodes[i].left = nodes[2 * i + 1];
			nodes[i].right = nodes[2 * i + 2];
		}

		return nodes[0];
	}

	/**
	 * <pre>
	 * 层次遍历
	 * 
	 * </pre>
	 */
	public static void levelOrder(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> nodes = new LinkedList<>();
		nodes.add(root);
		BinaryTreeNode currNode;
		System.out.print("层次遍历:");
		while (!nodes.isEmpty()) {
			currNode = nodes.poll();
			if (null != currNode.left) {
				nodes.add(currNode.left);
			}
			if (null != currNode.right) {
				nodes.add(currNode.right);
			}
			System.out.print(currNode.val + "->");
		}
		System.out.println();
	}

	/**
	 * <pre>
	 * 前序遍历
	 * 
	 * </pre>
	 * 
	 */
	public static void beforeOrder(BinaryTreeNode root) {
		Stack<BinaryTreeNode> nodes = new Stack<>();
		nodes.push(root);
		BinaryTreeNode currNode;
		System.out.print("前序遍历:");
		while (!nodes.isEmpty()) {
			currNode = nodes.pop();
			System.out.print(currNode.val + "->");
			if (null != currNode.right) {
				nodes.push(currNode.right);
			}
			if (null != currNode.left) {
				nodes.push(currNode.left);
			}
		}
		System.out.println();
	}

	/**
	 * <pre>
	 * 中序遍历
	 * 
	 * </pre>
	 */
	public static void midOrder(BinaryTreeNode root) {
		Stack<BinaryTreeNode> nodes = new Stack<>();
		BinaryTreeNode currNode = root;
		System.out.print("中序遍历:");
		while (null != currNode || !nodes.isEmpty()) {
			// 遍历节点，如果左叶子节点不为空，将节点都压入栈
			while (null != currNode) {
				nodes.push(currNode);
				currNode = currNode.left;
			}
			// 将最后一个左节点弹出，再遍历右节点
			if (!nodes.isEmpty()) {
				currNode = nodes.pop();
				System.out.print(currNode.val + "->");
				currNode = currNode.right;
			}
		}
		System.out.println();
	}

	/**
	 * <pre>
	 * 后序遍历
	 * 
	 * </pre>
	 */
	public static void afterOrder(BinaryTreeNode root) {
		Stack<BinaryTreeNode> nodes = new Stack<>();
		Stack<Integer> stack = new Stack<>();
		BinaryTreeNode currNode = root;
		int left = 1;
		int right = 2;
		System.out.print("后序遍历:");
		while (null != currNode || !nodes.isEmpty()) {
			// 遍历节点，如果左叶子节点不为空，将节点都压入栈
			while (null != currNode) {
				nodes.push(currNode);
				stack.push(left);
				currNode = currNode.left;
			}

			while (!nodes.isEmpty() && stack.peek() == right) {
				stack.pop();
				System.out.print(nodes.pop().val + "->");
			}

			if (!nodes.isEmpty() && stack.peek() == left) {
				stack.pop();
				stack.push(right);
				currNode = nodes.peek().right;
			}
		}
		System.out.println();
	}
}
