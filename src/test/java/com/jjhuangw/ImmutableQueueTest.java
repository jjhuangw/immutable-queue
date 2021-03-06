package com.jjhuangw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("unchecked")
public class ImmutableQueueTest {
	
	@Rule
	public ExpectedException exceptionRule1 = ExpectedException.none();
	
	@Rule
	public ExpectedException exceptionRule2 = ExpectedException.none();
	
	@Test
	public void testQueue() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		int[] tests = new int[]{1,2,3,4,5};
		for(int t : tests) {
			imQueue = imQueue.enQueue(t);
		}
		assertArrayEquals(tests, getValues(imQueue, tests.length));
	}
	
	@Test
	public void testEnqueueWithNull() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		exceptionRule1.expect(IllegalArgumentException.class);
		imQueue = imQueue.enQueue(null);
	}
	
	@Test
	public void testDequeueWithNull() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		exceptionRule2.expect(NoSuchElementException.class);
		imQueue = imQueue.deQueue();
	}
	
	@Test
	public void testHead() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		int[] tests = new int[]{1,2,3,4,5};
		for(int t : tests) {
			imQueue = imQueue.enQueue(t);
		}
		assertEquals(1, (int)imQueue.head());
	}
	
	@Test
	public void testTail() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		int[] tests = new int[]{1,2,3,4,5};
		for(int t : tests) {
			imQueue = imQueue.enQueue(t);
		}
		assertEquals(5, (int)imQueue.tail());
	}
	
	@Test
	public void testIsEmpty() {
		Queue<Integer> imQueue = ImmutableQueue.build();
		int[] tests = new int[]{1,2,3,4,5};
		for(int t : tests) {
			imQueue = imQueue.enQueue(t);
		}
		assertEquals(false, imQueue.isEmpty());
	}
	
	private static int[] getValues(Queue<Integer> q, int size) {
		int[] values = new int[size];
		int idx = 0;
		while(q != null && !q.isEmpty()){
			values[idx] = q.head();
			q = q.deQueue();
			idx++;
		}
		return values;
	}

}
