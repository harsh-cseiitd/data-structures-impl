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


public class LinkedStack<E> implements Stack<E>, Iterable<E> {
	
	private StackNode topNode;
	private int size = 0;
	
	public LinkedStack() {
		topNode = null;
		size = 0;
	}

	@Override
	public E get() {
		return pop();
	}

	@Override
	public void clear() {
		while (topNode != null) {
			pop();
		}
		size = 0;
	}

	@Override
	public boolean empty() {
		return (topNode == null);
	}

	@Override
	public boolean isThreadSafe() {
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E element) {
		StackNode nextToTop = topNode;
		topNode          = new StackNode();
		topNode.element  = element;
		topNode.next     = nextToTop;
		size++;
	}

	@Override
	public E pop() {
		if (topNode != null) {
			StackNode node = topNode;
			topNode        = topNode.next;
			node.next      = null; // removing dangling reference
			size--;
			return node.element;
		}
		return null;
	}

	@Override
	public E top() {
		if (topNode != null) {
			return topNode.element;
		}
		return null;
	}

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
