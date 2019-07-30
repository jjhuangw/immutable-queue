package com.jjhuangw;

import java.util.NoSuchElementException;

/**
 * Uses two stack data structures to track of the elements.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ImmutableQueue<T> implements Queue<T> {

	private final Stack<T> forwards;
	private final Stack<T> reverse;

	private ImmutableQueue(Stack<T> forwards, Stack<T> reverse) {
		this.forwards = forwards;
		this.reverse = reverse;
	}

	/**
	 * Reverses the provided stack.
	 * 
	 * @param stack
	 * @return 
	 */
	public final static Stack reverseStack(Stack stack) {
		Stack emptyStack = ImmutableStack.build();
		while (!stack.isEmpty()) {
			emptyStack = emptyStack.push(stack.head());
			stack = stack.pop();
		}
		return emptyStack;
	}

	/** 
	 * Create an empty Immutable Queue
	 * 
	 * */
	public final static Queue build() {
		return EmptyQueue.getInstance();
	}
	
	/**
	 * Add the element at the end of the immutable queue
	 * 
	 * @return new queue after inserting the element
	 * @exception IllegalArgumentException - if some property of this element prevents it from being added to this queue
	 */
	public final Queue<T> enQueue(T object) {
		if (object == null)
			throw new IllegalArgumentException();
		return new ImmutableQueue<T>(forwards, reverse.push(object));
	}
	
	/**
	 * Removes the element at the beginning of the immutable queue
	 * 
	 * @return return the new queue when removing the element from queue
	 */
	public final Queue<T> deQueue() {
		Stack<T> f = forwards.pop();
		if (!f.isEmpty()) {
			return new ImmutableQueue<T>(f, reverse);
		} else if (reverse.isEmpty()) {
			return ImmutableQueue.build();
		} else {
			return new ImmutableQueue<T>(ImmutableQueue.reverseStack(reverse), ImmutableStack.build());
		}
	}
	
	/**
	 * Get the head element of the immutable queue
	 * 
	 * @return return the first element of the queue
	 * @exception NoSuchElementException - if this queue is empty
	 */
	public final T head() {
		if (forwards.isEmpty())
			throw new NoSuchElementException();
		return forwards.head();
	}

	/**
	 * Return the last element in the queue
	 * 
	 * @return Return the last element in the queue
	 * @exception NoSuchElementException - if this queue is empty
	 */
	public T tail() {
		if (reverse.isEmpty())
			throw new NoSuchElementException();
		return reverse.head();
	}
	
	/**
	 * Return true when the queue is empty
	 * 
	 * */
	public final boolean isEmpty() {
		return false;
	}

	private static final class EmptyQueue<T> implements Queue<T> {

		private final static EmptyQueue emptyQueue = new EmptyQueue();

		public final static EmptyQueue getInstance() {
			return emptyQueue;
		}

		public final Queue<T> enQueue(T t) {
			if (t == null)
				throw new IllegalArgumentException();
			return new ImmutableQueue<T>(ImmutableStack.build().push(t), ImmutableStack.build());
		}

		public final Queue<T> deQueue() {
			throw new NoSuchElementException();
		}

		public final T head() {
			throw new NoSuchElementException();
		}

		public T tail() {
			throw new NoSuchElementException();
		}

		public final boolean isEmpty() {
			return true;
		}
	}

}
