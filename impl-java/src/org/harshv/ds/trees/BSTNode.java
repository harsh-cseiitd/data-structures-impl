package org.harshv.ds.trees;

public class BSTNode<K extends Comparable<K>, T> {
	private  Object nodeData;
	private  Object nodeKey;
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
