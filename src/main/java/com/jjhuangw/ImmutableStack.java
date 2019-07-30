package com.jjhuangw;

import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class ImmutableStack<T> implements Stack<T> {

	private final T head;
	private final Stack<T> tail;

	private ImmutableStack(T head, Stack<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	public final Stack<T> push(T t) {
		return new ImmutableStack<T>(t, this);
	}

	public final Stack<T> pop() {
		return tail;
	}

	public final T head() {
		if(head == null) 
			throw new NoSuchElementException();
		return head;
	}

	public final T tail() {
		if(tail == null)
			throw new NoSuchElementException();
		return tail.head();
	}

	public final boolean isEmpty() {
		return false;
	}

	public final static Stack build() {
		return EmptyStack.getInstance();
	}

	private static final class EmptyStack<T> implements Stack<T> {

		private final static EmptyStack emptyStack = new EmptyStack();

		public final static EmptyStack getInstance() {
			return emptyStack;
		}

		public final Stack<T> push(T t) {
			return new ImmutableStack<T>(t, this);
		}

		public final Stack<T> pop() {
			throw new NoSuchElementException();
		}

		public final T head() {
			throw new NoSuchElementException();
		}

		public final T tail() {
			throw new NoSuchElementException();
		}

		public final boolean isEmpty() {
			return true;
		}
	}

}
