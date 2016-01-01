package org.harshv.ds.queues;

public interface PriorityQueue<T extends Comparable<T>> {
	void	add(T element);
	void	remove(T element);
	void decreasePriority(T element, V newValue);
	void increasePriority(T element, V newValue);
	T	getTopPriorityElement();
	T	removeTopPriorityElement();
	int	size();
	void	clear();
	boolean	exist(T element);
}
