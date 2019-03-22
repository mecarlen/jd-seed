package com.jd.seed.exercise.collection;

import java.util.Arrays;
import java.util.LinkedList;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

/**
 * <pre>
 * 
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月13日 下午3:59:16
 */
public class ListTest {
	@Test
	public void linkedList() {
		LinkedList<String> ll = new LinkedList<>();
		ll.addAll(Arrays.asList(new String[] {"jecarlen","mecarlen","mustong","jhon"}));
		Assertions.assertThat(ll.getFirst()).isEqualTo("jecarlen").describedAs("头部元素不是期望值");
		Assertions.assertThat(ll.getLast()).isEqualTo("jhon").describedAs("尾部元素不是期望值");
	}
	@Test
	public void intOpt() {
		int i=0;
		i = i++;
		System.out.println("--->"+i);
	}
}
