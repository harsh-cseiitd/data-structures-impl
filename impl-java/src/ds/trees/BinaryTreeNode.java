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

public class BinaryTreeNode<T> {
	
	private  T nodeData;
	private  BinaryTreeNode<T> left;
	private  BinaryTreeNode<T> right;
	
	public BinaryTreeNode() {
		nodeData = null;
		left  = null;
		right = null;
	}

	public BinaryTreeNode(T objData) {
		this();
		setProperties(objData);
	}

	public T getData() {
		return (T) this.nodeData;
	}

	public void setProperties(T objData) {
		if (nodeData.getClass() != Object.class) {
			nodeData = objData;
		}
	}
	
	public void setLeft(BinaryTreeNode<T> node) {
		this.left = node;
	}
	
	public void setRight(BinaryTreeNode<T> node) {
		this.right = node;
	}
	
	public BinaryTreeNode<T> getLeft() {
		return this.left;
	}
	
	public BinaryTreeNode<T> getRight() {
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
