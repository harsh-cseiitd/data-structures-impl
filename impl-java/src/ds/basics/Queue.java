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

package org.harshv.ds.basics;

import java.util.LinkedList;

public class Queue<V> {

	private LinkedList<V> items;

	public Queue() {
		this.items = new LinkedList<V>();
	}

	public void insert(V item) {
		this.items.addFirst(item);
	}

	public V remove() {
		if (! this.items.isEmpty()) {
			return this.items.removeLast();
		}
		return null;
	}

	public V getFirst() {
		if (! this.items.isEmpty()) {
			return this.items.getLast();
		}
		return null;
	}

	public boolean isEmpty() {
		return this.items.isEmpty();
	}
}
