/*
 * Copyright (C) 2016 Harsh Vardhan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ds.linearstructures;

import java.util.Iterator;

/* Queue is backed up by a Single linked list. All operations except
 * toArray() are performed in constant time.
 * toArray() is performed in liner time.
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E>, Iterable<E> {

	private QueueNode frontNode;
	private QueueNode rearNode;
	private int size;
	
	public LinkedQueue() {
		this.frontNode = null;
		this.rearNode  = null;
		this.size      = 0;
	}

	@Override
	public E get() {
		return dequeue();
	}

	@Override
	public void clear() {
		while (!empty()) {
			dequeue();
		}
		this.size = 0;
	}

	@Override
	public boolean empty() {
		return (this.rearNode == null && this.frontNode == null);
	}

	@Override
	public boolean isThreadSafe() {
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Object[] toArray() {
		if (empty()) {
			return null;
		}
		Object[] array = new Object[size];
		Iterator<E> queueIterator = this.iterator();
		int index= 0;
		while(queueIterator.hasNext()) {
			array[index] = queueIterator.next();
			index++;
		}
		return array;
	}

	@Override
	public void enqueue(E element) {
		QueueNode temp = new QueueNode();
		temp.element   = element;
		temp.prev      = null;
		if (empty()) {
			this.rearNode  = temp;
			this.frontNode = temp;
		} else {
			this.rearNode.prev = temp;
			this.rearNode      = temp;
		}
		this.size = this.size + 1;
	}

	@Override
	public E dequeue() {
		if (empty()) {
			return null;
		} else if (oneElementInQueue()) {
			QueueNode temp = this.frontNode;
			this.frontNode = null;
			this.rearNode  = null;
			this.size      = this.size - 1;
			return temp.element;
		} else {
			QueueNode temp = this.frontNode;
			this.frontNode = this.frontNode.prev;
			this.size      = this.size - 1;
			return temp.element;
		}
	}

	@Override
	public E front() {
		if (empty()) {
			return null;
		} else {
			return this.frontNode.element;
		}
	}

	@Override
	public E rear() {
		if (empty()) {
			return null;
		} else {
			return this.rearNode.element;
		}
	}
	
	/* Returns elements in FIFO order */
	@Override
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private boolean oneElementInQueue() {
		return (this.rearNode == this.frontNode);
	}

	private class QueueNode {
		E element;
		QueueNode prev;
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode currentNode;
		
		QueueIterator() {
			currentNode = frontNode;
		}

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public E next() {
			if (currentNode != null) {
				E returnElement = (E) currentNode.element;
				currentNode = currentNode.prev;
				return returnElement;
			}
			return null;
		}
	}
}
