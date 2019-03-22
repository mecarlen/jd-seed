package com.jd.seed.exercise.algorithm.other;

/**
 * <pre>
 * 一个圆环上有100个灯泡，灯泡有打开关闭两种状态，灯泡的状态随机的,按一个灯泡，相邻两个灯泡的状态也发生一次变化。
 * 如，暗-亮-暗，按中间灯泡，变为亮-暗-亮，设计一套算法，使得所有灯泡都亮。
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月21日 下午5:21:30
 */
public class RoundBulbOpen {
	/**
	 * <pre>
	 * 1、暗-亮-暗，按中间灯泡，变为亮-暗-亮，按这个规则逐个按，碰到暗就按下一个，否则就跳过
	 * 2、第一步完成之后根据排列组合，第一个和第二个灯亮暗会有四种组合
	 * 	  暗暗、暗亮、亮暗、亮亮（这种是目标结果）
	 *    前三种我们需要预处理为暗亮暗暗
	 * </pre>
	 */
	public static void press(boolean[] bulbs) {
		// 按照
		for (int i = 0; i < bulbs.length; i++) {
			if (!bulbs[i]) {
				// 当前灯是暗就按下一盏灯
				RoundBulbOpen.press(i + 1, bulbs);
			}
		}

		if (!bulbs[0] && !bulbs[1]) {
			// 001111
			RoundBulbOpen.press(2, bulbs);// 010011
			RoundBulbOpen.roundPress(2, bulbs);
		} else if (!bulbs[0] && bulbs[1]) {
			// 0111111
			RoundBulbOpen.press(1, bulbs);// 1001111
			RoundBulbOpen.press(3, bulbs);// 1010011
			RoundBulbOpen.roundPress(3, bulbs);
		} else if (bulbs[0] && !bulbs[1]) {
			// 10111111
			RoundBulbOpen.press(2, bulbs);// 11001111
			RoundBulbOpen.press(4, bulbs);// 11010011
			RoundBulbOpen.roundPress(4, bulbs);
		}
	}

	/**
	 * <pre>
	 *  将暗亮暗暗->暗暗暗亮算法
	 * 
	 * 算法
	 * 1、循环从暗亮暗暗的亮的那盏后面第一盏暗灯开始
	 * 
	 * 
	 * </pre>
	 * 
	 */
	public static void roundPress(int currIdx, boolean[] bulbs) {
		// 计算当前位置后面第二盏灯下标
		int nextPressIdx1 = (currIdx + 2) >= bulbs.length ? currIdx + 2 - bulbs.length : currIdx + 2;
		// 计算当前位置后面第三盏灯下标
		int nextPressIdx2 = (currIdx + 3) >= bulbs.length ? currIdx + 3 - bulbs.length : currIdx + 3;
		//如果与当前位置后相隔的一盏灯亮着，则按中间一盏灯
		if (bulbs[nextPressIdx1]) {
			//执行以下两步使连续的两盏暗灯向后偏一位
			RoundBulbOpen.press(currIdx + 1, bulbs);
			RoundBulbOpen.press(nextPressIdx2, bulbs);
			//迭代执行
			RoundBulbOpen.roundPress(nextPressIdx2, bulbs);
		} else {
			// 如果当前位置后连续三盏灯都是暗，按中间一个结构迭代
			RoundBulbOpen.press(currIdx + 1, bulbs);
		}
	}

	/**
	 * <pre>
	 * 暗-亮-暗，按中间灯泡，变为亮-暗-亮
	 * 
	 * </pre>
	 * 
	 * @param pressIdx
	 *            int
	 * @param bulbs
	 *            boolean[]
	 */
	public static void press(int pressIdx, boolean[] bulbs) {
		// 控制按的都在圆环上
		if (pressIdx >= bulbs.length) {
			pressIdx = pressIdx - bulbs.length;
		}
		int left = pressIdx - 1;
		if (left < 0) {
			left = bulbs.length - 1;
		}
		int right = pressIdx + 1;
		if (right >= bulbs.length) {
			right = right - bulbs.length;
		}
		// 灯泡状态变化，原状态取反
		bulbs[left] = !bulbs[left];
		bulbs[pressIdx] = !bulbs[pressIdx];
		bulbs[right] = !bulbs[right];
	}

	/**
	 * <pre>
	 * 灯泡状态初始化
	 * 
	 * 算法
	 * 	state = Double.valueOf(Math.random() * 100).intValue() % 3 == 1
	 * </pre>
	 * 
	 * @param length
	 *            int
	 * @return boolean[]
	 */
	public static boolean[] initBulbsRoundState(int length) {
		if (length <= 0) {
			return new boolean[0];
		}
		boolean[] bulbs = new boolean[length];
		for (int i = 0; i < length; i++) {
			bulbs[i] = Double.valueOf(Math.random() * 100).intValue() % 3 == 1;
		}
		return bulbs;
	}

	public static void print(boolean[] bulbs) {
		for (int i = 0; i < bulbs.length; i++) {
			if (i > 0 && i % 10 == 9) {
				System.out.println("," + (i>=9?i+1:"0"+(i+1)) + "-" + (bulbs[i] == true ? "亮" : "暗"));
			} else if (i % 10 == 0) {
				System.out.print((i>=9?i+1:"0"+(i+1)) + "-" + (bulbs[i] == true ? "亮" : "暗"));
			} else {
				System.out.print("," + (i>=9?i+1:"0"+(i+1)) + "-" + (bulbs[i] == true ? "亮" : "暗"));
			}
		}
	}
}
