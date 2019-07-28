package com.jjhuangw;

import java.util.NoSuchElementException;

/**
 * This class uses two stacks to track of the elements.
 */
public class ImmutableQueue<T> implements Queue<T> {

	/**
	 * LIFO
	 */
	private static class Stack<T> {

		private T head;
		private Stack<T> tail;
		private int size;

		private Stack() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}

		private Stack(T obj, Stack<T> tail) {
			this.head = obj;
			this.tail = tail;
			this.size = tail.size + 1;
		}

		public Stack<T> push(T obj) {
			return new Stack<T>(obj, this);
		}

		public boolean isEmpty() {
			return this.size == 0;
		}
		
		public Stack<T> reverseStack() {
			Stack<T> stack = new Stack<T>();
			Stack<T> tail = this;
			while (!tail.isEmpty()) {
				stack = stack.push(tail.head);
				tail = tail.tail;
			}
			return stack;
		}
	}

	private Stack<T> forward;
	private Stack<T> reverse;

	public ImmutableQueue() {
		this.forward = new Stack<T>();
		this.reverse = new Stack<T>();
	}

	public ImmutableQueue(Stack<T> forward, Stack<T> reverse) {
		this.forward = forward;
		this.reverse = reverse;
	}

	/**
	 * Add the element at the end of the immutable queue
	 * 
	 * @return new queue after inserting the element
	 * @exception IllegalArgumentException - if some property of this element prevents it from being added to this queue
	 */
	public Queue<T> enQueue(T object) {
		if (object != null) {
			return new ImmutableQueue<T>(this.forward.push(object), this.reverse);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Removes the element at the beginning of the immutable queue
	 * 
	 * @return return the new queue when removing the element from queue
	 * @exception NoSuchElementException - if this queue is empty
	 */
	public Queue<T> deQueue() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (!this.reverse.isEmpty()) {
			return new ImmutableQueue<T>(this.forward, this.reverse.tail);
		} else {
			return new ImmutableQueue<T>(new Stack<T>(), this.forward.reverseStack().tail);
		}
	}

	/**
	 * Get the head element of the immutable queue
	 * 
	 * @return return the first element of the queue
	 * @exception NoSuchElementException - if this queue is empty
	 */
	public T head() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.reverse.isEmpty()) {
			this.reverse = this.forward.reverseStack();
			this.forward = new Stack<T>();
		}
		return this.reverse.head;
	}
	
	/**
	 * Return queue size
	 * @return return the queue size
	 */
	public int getSize() {
		return this.forward.size + this.reverse.size;
	}
	/**
	 * Return the last element in the queue
	 * 
	 * @return Return the last element in the queue
	 */
	public boolean isEmpty() {
		return this.forward.size + this.reverse.size == 0;
	}

	/**
	 * Return the last element in the queue
	 * 
	 * @return Return the last element in the queue
	 * @exception NoSuchElementException - if this queue is empty
	 */
	public T tail() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.forward.isEmpty()) {
			return this.reverse.reverseStack().head;
		} else {
			return this.forward.head;
		}
	}
	
}
