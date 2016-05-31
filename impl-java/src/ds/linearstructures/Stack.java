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

public interface Stack<E> extends OrderedCollection<E> {

	/**
     * Add an element to the front of the stack.
     * 
     * @param element The element to be added.
     */
    public void push(E element);
    

    /**
     * Remove the front element from the stack.
     *
     * @return The element removed from the stack.
     */
    public E pop();
    
    /**
     * Read the front element from the stack.
     *
     * @return The element from the stack.
     */
    public E top();

}
