package com.jjhuangw;

/**
 * Interface for immutable queue (FIFO)
 * */
public interface Queue<T> {
	/** Add the element at the end of the immutable queue
	 * @return new queue after inserting the element
	 * */
    public Queue<T> enQueue(T t);
    /** Removes the element at the beginning of the immutable queue
     * @return return the new queue when removing the element from queue
     */
    public Queue<T> deQueue();
    /** Get the head element of the immutable queue
     * @return return the first element of the queue
     * */
    public T head();
    /** Returns true if this queue contains no elements.
     * @return true if this queue is empty. 
     * */
    public boolean isEmpty();
    /** Return the last element in the queue
     * @return Return the last element in the queue
     * */
    public T tail();
    /**
	 * Return queue size
	 * @return return the queue size
	 */
    public int getSize();
}