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

/* Stack is backed up by a Single linked list. All operations except
 * toArray() are performed in constant time.
 * toArray() is performed in liner time.
 * @param <E>
 */

public class LinkedStack<E> implements Stack<E>, Iterable<E> {
	
	private StackNode topNode;
	private int size;
	
	public LinkedStack() {
		this.topNode = null;
		this.size = 0;
	}

	@Override
	public E get() {
		return pop();
	}

	@Override
	public void clear() {
		while (empty()) {
			pop();
		}
		this.size = 0;
	}

	@Override
	public boolean empty() {
		return (this.topNode == null);
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
		Iterator<E> stackIterator = this.iterator();
		int index= 0;
		while(stackIterator.hasNext()) {
			array[index] = stackIterator.next();
			index++;
		}
		return array;
	}

	@Override
	public void push(E element) {
		StackNode nextToTop = this.topNode;
		this.topNode        = new StackNode();
		this.topNode.element= element;
		this.topNode.next   = nextToTop;
		this.size = this.size + 1;
	}

	@Override
	public E pop() {
		if (!empty()) {
			StackNode temp = this.topNode;
			this.topNode   = this.topNode.next;
			temp.next      = null; // removing dangling reference
			this.size      = this.size - 1;
			return temp.element;
		}
		return null;
	}

	@Override
	public E top() {
		if (!empty()) {
			return this.topNode.element;
		}
		return null;
	}

	/** returns elements in LIFO order */
	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}

	private class StackNode {
		E element;
		StackNode next;
	}

	private class StackIterator implements Iterator<E> {
		private StackNode currentNode;
		
		StackIterator() {
			currentNode = topNode;
		}

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public E next() {
			if (currentNode != null) {
				E returnElement = (E) currentNode.element;
				currentNode = currentNode.next;
				return returnElement;
			}
			return null;
		}
	}
}
