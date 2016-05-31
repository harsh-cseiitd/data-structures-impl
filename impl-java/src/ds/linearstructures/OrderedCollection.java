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

public interface OrderedCollection<E> {

	/**
     * Fetch an element of collection. Return element is
     * implementation dependent.
     */
    public E get();

    /**
     * Removes all elements of collection. makes it empty.
     */
    public void clear();

    /**
     * Returns true only if the collection is empty, otherwise
     * returns false.
     */
    public boolean empty();
    
    /**
     * Returns true only if the collection implementation is
     * thread safe.
     */
    public boolean isThreadSafe();

    /**
     * Returns the number of elements in collection.
     */
    public int size();
    
    /**
     * Returns array representation of the collection.
     */
    public E[] toArray();
}
