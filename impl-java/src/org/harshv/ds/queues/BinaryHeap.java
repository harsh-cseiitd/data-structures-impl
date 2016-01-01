package org.harshv.ds.queues;

import java.util.ArrayList;
import java.util.Collection;

public class BinaryHeap<T extends Comparable<T>> {
	private final int  BUFFER_CAPACITY = 16;
	private int size = 0;
	private boolean isMaxHeap = true;
	private int capacity = BUFFER_CAPACITY;
	private ArrayList<T> heapStorage;

	/* constructors */
	public BinaryHeap() {
		this.heapStorage  = new ArrayList<T>(this.capacity);
	}

	public BinaryHeap(boolean maxHeap) {
		this.isMaxHeap    = maxHeap;
		this.heapStorage  = new ArrayList<T>(this.capacity);
	}

	public BinaryHeap(Collection<? extends T> collection) {
		this(collection, true);
	}

	public BinaryHeap(Collection<? extends T> collection, boolean maxHeap) {
		this.isMaxHeap  = maxHeap;
		this.size       = collection.size();
		this.capacity   = this.size + BUFFER_CAPACITY;
		this.heapStorage  = new ArrayList<T>(this.capacity);
		this.heapStorage.add(0, null);
		this.heapStorage.addAll(getRootIndex(), collection);
		System.out.println("array list created of size:" + this.heapStorage.size() + " and capacity: " + this.capacity);
		buildHeap();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(this.size);
		for(int i = 1; i < this.heapStorage.size(); i++) {
			sb.append(this.heapStorage.get(i).toString());
			sb.append(", ");	
		}
		if (this.isMaxHeap) {
			return "\nMax Heap " + sb.toString();
		}
		return "\nMin Heap " + sb.toString();
	}

	public int heapSize() {
		return this.size;
	}
	
	public boolean isMaxHeap() {
		return this.isMaxHeap;
	}
	
	public void ensureCapacity(int targetCapacity) {
		if (this.capacity < targetCapacity) {
			this.capacity = targetCapacity;
			this.heapStorage.ensureCapacity(this.capacity);
		}
	}

	public void add(T element) {
		if (this.size == this.capacity) {
			this.capacity   = this.size + BUFFER_CAPACITY;
			this.heapStorage.ensureCapacity(this.capacity);
		}
		this.size = this.size  + 1;
		this.heapStorage.set(this.size, element);
		int currentIndex = this.size;
		percolateUp(currentIndex);
	}
	
	public boolean exist(T element) {
		return this.heapStorage.contains(element);
	}
	
	public T peek() {
		return (T) this.heapStorage.get(getRootIndex());
	}
	
	public T pop() {
		T topObj =  (T) this.heapStorage.get(getRootIndex());
		swapElements(getRootIndex(), this.size);
		this.heapStorage.set(this.size, null);
		this.size = this.size - 1;
		percolateDown(getRootIndex());
		return topObj;
	}
	
	public boolean modifyKey(T element, T newElement) {
		boolean increaseKey = false;
		if (element.compareTo(newElement) == 0) {
			return false;
		} else if (element.compareTo(newElement) > 0) {
			increaseKey = true;
		}
		int searchIndex = this.heapStorage.indexOf(element);
		if (searchIndex <= 0 && searchIndex > this.size) {
			return false;
		}
		this.heapStorage.set(searchIndex, newElement);
		if (this.isMaxHeap) {
			if (increaseKey) {
				percolateUp(searchIndex);
			} else {
				percolateDown(searchIndex);
			}
		} else {
			if (increaseKey) {
				percolateDown(searchIndex);
			} else {
				percolateUp(searchIndex);
			}
		}
		return true;
	}

	/* private methods of BinaryHeap */
	private void buildHeap() {
		for (int ci = getRootIndex(); ci <= this.size; ci++) {
			percolateUp(ci);
		}
	}
	
	private void percolateUp(int currentIndex) {
		while (currentIndex > getRootIndex()) {
			T currentObj  = this.heapStorage.get(currentIndex);
			T parentObj   = this.heapStorage.get(getParent(currentIndex));
			if (this.isMaxHeap) {
				if (currentObj.compareTo(parentObj) > 0) {
					swapElements(currentIndex, getParent(currentIndex));
				} else {
					break;
				}
			} else {
				if (currentObj.compareTo(parentObj) < 0) {
					swapElements(currentIndex, getParent(currentIndex));
				} else {
					break;
				}
			}
			currentIndex = getParent(currentIndex);
		}
	}	

	private void percolateDown(int subIndex) {
		int leftIndex  = getLeftChild(subIndex);
		int rightIndex = getRightChild(subIndex);

		while (leftIndex <= this.size) {
			T rootObj = this.heapStorage.get(subIndex);
			T leftObj = this.heapStorage.get(leftIndex);
			
			if (this.isMaxHeap) {
				int maxIndex = subIndex;
				if (rootObj.compareTo(leftObj) < 0) {
					maxIndex = leftIndex;
				}
				if (rightIndex <= this.size) {
					T rightObj = this.heapStorage.get(rightIndex);
					if (rightObj.compareTo(this.heapStorage.get(maxIndex)) > 0) {
						maxIndex = rightIndex;
					}
				}
				if (maxIndex > subIndex) {
					swapElements(subIndex, maxIndex);
					subIndex = maxIndex;
				} else {
					break;
				}
			} else {
				int minIndex = subIndex;
				if (rootObj.compareTo(leftObj) > 0) {
					minIndex = leftIndex;
				}
				if (rightIndex <= this.size) {
					T rightObj = this.heapStorage.get(rightIndex);
					if (rightObj.compareTo(this.heapStorage.get(minIndex)) < 0) {
						minIndex = rightIndex;
					}
				}
				if (minIndex > subIndex) {
					swapElements(subIndex, minIndex);
					subIndex = minIndex;
				} else {
					break;
				}
			}	
			leftIndex  = getLeftChild(subIndex);
			rightIndex = getRightChild(subIndex);	
		}
	}

	private int getRootIndex() {
		return 1;
	}
	
	private int getParent(int elementIndex) {
		if (elementIndex <= getRootIndex()) {
			return 0;
		}
		return Math.floorDiv(elementIndex, 2);
	}

	
	private int getLeftChild(int elementIndex) {
		return elementIndex * 2;
	}

	
	private int getRightChild(int elementIndex) {
		return ((elementIndex * 2)+1);
	}


	private void swapElements(int index1, int index2) {
		T obj = this.heapStorage.get(index1);
		this.heapStorage.set(index1, this.heapStorage.get(index2));
		this.heapStorage.set(index2, obj);
	}

}
