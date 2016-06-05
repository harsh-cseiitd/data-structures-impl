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


import java.util.ArrayList;
import java.util.Iterator;

/* Stack is backed up by an ArrayList. All operations except
 * toArray() are performed in constant time.
 * toArray() is performed in liner time.
 * @param <E>
 */

public class ExpandableArrayStack<E> implements Stack<E>, Iterable<E> {

	private ArrayList<E> elements;
	private int topIndex;
	private int size;
	private static final int EMPTYINDEX = -1;
	
	public ExpandableArrayStack() {
		this.elements = new ArrayList<E>();
		this.topIndex = EMPTYINDEX;
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
		return (this.topIndex == EMPTYINDEX);
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

	/* returns elements in LIFO order */
	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}

	@Override
	public void push(E element) {
		this.topIndex = this.topIndex + 1;
		this.elements.ensureCapacity(this.topIndex);
		this.elements.add(topIndex, element);
		this.size = this.size +1;
	}

	@Override
	public E pop() {
		if (!empty()) {
			E element = this.elements.get(topIndex);
			this.elements.set(topIndex, null);
			this.size = this.size -1;
			return element;
		}
		return null;
	}

	@Override
	public E top() {
		if (!empty()) {
			return this.elements.get(topIndex);
		}
		return null;
	}

	private class StackIterator implements Iterator<E> {
		private int currentIndex;
		
		StackIterator() {
			currentIndex = topIndex;
		}

		@Override
		public boolean hasNext() {
			return (currentIndex > EMPTYINDEX);
		}

		@Override
		public E next() {
			if (currentIndex > EMPTYINDEX) {
				E returnElement = (E) elements.get(currentIndex);
				currentIndex    = currentIndex - 1;
				return returnElement;
			}
			return null;
		}
	}
}
