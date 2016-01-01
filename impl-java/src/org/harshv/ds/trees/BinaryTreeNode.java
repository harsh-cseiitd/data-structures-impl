package org.harshv.ds.trees;

public class BinaryTreeNode<T> {
	
	private  Object nodeData;
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
