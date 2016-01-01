package org.harshv.ds.queues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public <T extends Comparable<T>> class HeapBasedPriorityQueue<T> implements PriorityQueue<T> {
	
	private BinaryHeap<T> bHeap;
	
	public HeapBasedPriorityQueue() {
		this.bHeap = new BinaryHeap<T>();
	}
	
	public HeapBasedPriorityQueue(int initialcapacity) {
		this.bHeap = new BinaryHeap<T>();
		this.bHeap.ensureCapacity(initialcapacity);
	}
	
	public HeapBasedPriorityQueue (Collection<? extends T> collection) {
		this.bHeap = new BinaryHeap<T>(collection);
	}
	
	public HeapBasedPriorityQueue(Comparator<? super T> comparator) {
		
	}
	
	private void buildHeap() {
		
	}
	
	private boolean restoreHeap() {
		
	}
	
	public boolean	add(T element) {
		return true;
	}

	public void  clear() {
		
	}

	public boolean	exist(T element) {
		
	}

	public T  getTopElement() {
		
	}

	public boolean  removeTopElement() {
		
	}

	public boolean	removeElement(T element) {
		
	}

	public int	size() {
		return 0;
	}
	

}
