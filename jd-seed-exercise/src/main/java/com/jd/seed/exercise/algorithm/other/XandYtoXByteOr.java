package com.jd.seed.exercise.algorithm.other;

/**
 * <pre>
 * 寻找第k个满足的x+y=x|y
 * 
 * 算法
 * 1、将x转换成二进制高位补0，k转成二进制
 * 2、将k的二进制每位值从低位到高位填补x中二进制中为0的位对应也是从低位到高位
 * 3、y=将第二步中二进制转换成十进制-x
 * </pre>
 * 
 * @author mecarlen 2019年3月23日 下午4:06:36
 */
public class XandYtoXByteOr {
	public static int and2Or(int x, int k) {
		String xStr = Integer.toBinaryString(x);
		String kStr = Integer.toBinaryString(k);
		int kByteIdx = kStr.length();
		StringBuilder xySb = new StringBuilder();
		for (int i = xStr.length() - 1; i >= 0; i--) {
			if ('0' == xStr.charAt(i)) {
				xySb.insert(0, kStr.charAt(kByteIdx - 1));
				--kByteIdx;
				continue;
			} else {
				xySb.insert(0, '1');
			}
		}
		while (kByteIdx > 0) {
			xySb.insert(0, kStr.charAt(kByteIdx - 1));
			--kByteIdx;
		}
		int xy = Integer.parseInt(xySb.toString(), 2);
		return xy - x;
	}
}
