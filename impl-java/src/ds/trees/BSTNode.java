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

package org.harshv.ds.trees;

public class BSTNode<K extends Comparable<K>, T> {
	private  T nodeData;
	private  K nodeKey;
	private  BSTNode<K, T> left;
	private  BSTNode<K, T> right;
	
	public BSTNode() {
		nodeData = null;
		nodeKey  = null;
		left  = null;
		right = null;
	}

	public BSTNode(K key, T objData) {
		this();
		setProperties(key, objData);
	}

	public K getkey() {
		return (K) this.nodeKey;
	}

	public T getData() {
		return (T) this.nodeData;
	}

	public void setProperties(K key, T objData) {
		if (key.getClass() != Object.class) {
			nodeKey = key;
		} 
		if (nodeData.getClass() != Object.class) {
			nodeData = objData;
		}
	}
	
	public void setLeft(BSTNode<K,T> node) {
		this.left = node;
	}
	
	public void setRight(BSTNode<K,T> node) {
		this.right = node;
	}
	
	public BSTNode<K,T> getLeft() {
		return this.left;
	}
	
	public BSTNode<K,T> getRight() {
		return this.right;
	}
	
	public boolean isLeafNode() {
		return (getLeft() == null && getRight() == null);
	}
	
	public boolean hasLeftNode() {
		return (getLeft() != null) ;
	}
	
	public boolean hasRightNode() {
		return (getRight() != null) ;
	}
}
