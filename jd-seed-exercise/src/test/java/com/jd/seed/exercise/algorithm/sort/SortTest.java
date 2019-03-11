package com.jd.seed.exercise.algorithm.sort;

import org.junit.Test;

import com.jd.seed.exercise.algorithm.sort.bubble.BubbleSort;
import com.jd.seed.exercise.algorithm.sort.heap.HeapSort;
import com.jd.seed.exercise.algorithm.sort.insertion.StraightInsertionSort;
import com.jd.seed.exercise.algorithm.sort.quick.QuickSort;
import com.jd.seed.exercise.algorithm.sort.selection.SimpleSelectionSort;
import com.jd.seed.exercise.algorithm.sort.selection.TwoElementsSelectionSort;

/**
 * <pre>
 * 排序
 * 
 * </pre>
 * 
 * @author mecarlen 2017年2月25日 下午4:19:50
 */
public class SortTest {

	@Test
	public void bubbleSort() {
		BubbleSort bs = new BubbleSort();
		bs.execute();
	}

	@Test
	public void quickSort() {
		QuickSort qs = new QuickSort();
		qs.execute();
	}

	@Test
	public void insertionSort() {
		StraightInsertionSort sis = new StraightInsertionSort();
		sis.execute();
	}

	@Test
	public void selectionSort() {
		SimpleSelectionSort sss = new SimpleSelectionSort();
		sss.execute();
	}
	
	@Test
	public void twoElementsSelectionSort() {
		TwoElementsSelectionSort tss = new TwoElementsSelectionSort();
		tss.execute();
	}
	
	@Test
	public void mergeSort() {

	}

	@Test
	public void heapSort() {
		HeapSort hs = new HeapSort();
		hs.execute();
	}

	@Test
	public void radixSort() {

	}

	@Test
	public void shellSort() {

	}
}
