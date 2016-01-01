package org.harshv.ds.trees;

public class BinarySearchTree<K extends Comparable<K>, T>  {
	private  BSTNode<K, T> rootNode;
	private int size = 0;

	private class ParentNodePair {
		private BSTNode<K, T> node;
		private BSTNode<K, T> parent;
		
		ParentNodePair(BSTNode<K, T> n, BSTNode<K, T> p) {
			this.node = n;
			this.parent = p;
		}
		
		BSTNode<K, T> getNode() {
			return this.node;
		}
		
		BSTNode<K, T> getParent() {
			return this.parent;
		}
		
	}

	public BinarySearchTree() {
		rootNode = new BSTNode<K, T>();
		size = 1;
	}

	public BinarySearchTree(BSTNode<K, T> root) {
		rootNode = new BSTNode<K, T>();
		rootNode.setProperties(root.getkey(), root.getData());
		rootNode.setLeft(root.getLeft());
		rootNode.setRight(root.getRight());
		size = 1;
	}
	
	public BinarySearchTree(K key, T objData) {
		rootNode = new BSTNode<K, T>(key, objData);
		size = 1;
	}
	
	public int getSize() {
		return this.size;
	}

	public void addNode(K key, T objData) {
		if (rootNode == null) {
			rootNode = new BSTNode<K, T>(key, objData);
			this.size++;
		} else if (rootNode.getkey() == null && rootNode.getData() == null){
			rootNode.setProperties(key, objData);
		} else {
			BSTNode<K, T> current = rootNode;
			BSTNode<K, T> parent  = null;
			while(current != null) {
				if (current.getkey().compareTo(key) > 0) {
					parent = current;
					current = current.getLeft();
				} else {
					parent = current;
					current = current.getRight();
				}
			}
			
			if (parent.getkey().compareTo(key) > 0) {
				parent.setLeft(new BSTNode<K, T>(key, objData));
			} else {
				parent.setRight(new BSTNode<K, T>(key, objData));
			}
			this.size++;
		}
	}
	
	public void removeNode(K key) {
		ParentNodePair pn = findNodeAndParent(key);
		BSTNode<K, T> current = pn.getNode();
		BSTNode<K, T> parent  = pn.getParent();

		if (isRootNode(current) && current.isLeafNode()) {
			current = null;	
			this.rootNode = null;
			this.size = 0;
		} else {
			if (current.hasRightNode()) {
				ParentNodePair psn             = getInOrderNextRightSubTree(current);
				BSTNode<K, T> successor        = psn.getNode();
				BSTNode<K, T> successorParent  = psn.getParent();
				swapNodeData(current, successor);
				current = successor;
				removeNodeWithOutLeftSubTree(current, successorParent);
			} else if (current.hasLeftNode()) {
				ParentNodePair psn               = getInOrderPreLeftSubTree(current);
				BSTNode<K, T> predecessor        = psn.getNode();
				BSTNode<K, T> predecessorParent  = psn.getParent();
				swapNodeData(current, predecessor);
				current = predecessor;
				removeNodeWithOutRightSubTree(current, predecessorParent);
			} else {
				removeLeafNode(current, parent);
			}
			this.size = this.size -1;
		}	
	}
				
	/* Current Node doesn't have a left subtree */
	private void removeNodeWithOutLeftSubTree(BSTNode<K, T> current, BSTNode<K, T> parent) {
		if (current.hasRightNode() && !current.hasLeftNode()) {
			if (current == parent.getLeft()) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
			current = null;
		}	
	}
	
	/* Current Node doesn't have a right subtree */
	private void  removeNodeWithOutRightSubTree(BSTNode<K, T> current, BSTNode<K, T> parent) {
		if (current.hasLeftNode() && !current.hasRightNode()) {
			if (current == parent.getLeft()) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
			current = null;
		}
	}
	
	private void removeLeafNode(BSTNode<K, T> node, BSTNode<K, T> parent) {
		if (node == parent.getLeft()) {
			parent.setLeft(null);
		} else {
			parent.setRight(null);
		}
	}
	
	private ParentNodePair getInOrderNextRightSubTree(BSTNode<K, T> node) {
		BSTNode<K, T> successor = null;
		BSTNode<K, T> parent = null;
		
		if (node != null && node.hasRightNode()) {
			parent = node;
			successor  = node.getRight();
			while(successor.getLeft() != null) {
				parent = successor;
				successor = successor.getLeft();
			}
		}
		return new ParentNodePair(successor, parent);
	}
	
	private ParentNodePair getInOrderPreLeftSubTree(BSTNode<K, T> node) {
		BSTNode<K, T> predecessor = null;
		BSTNode<K, T> parent = null;
		if (node != null && node.hasLeftNode()) {
			parent = node;
			predecessor  = node.getLeft();
			while(predecessor.getRight() != null) {
				parent = predecessor;
				predecessor = predecessor.getRight();
			}
		}
		return new ParentNodePair(predecessor, parent);
	}
	
	
	public boolean exist(K key) {
		return (find(key) != null);
	}
	
	private BSTNode<K, T> find(K key) {
		BSTNode<K, T> current = this.rootNode;
		while(current != null) {
			if (current.getkey().compareTo(key) == 0) {
				break;
			}
			if (current.getkey().compareTo(key) > 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return current;
	}
	
	private ParentNodePair findNodeAndParent(K key) {
		BSTNode<K, T> current = this.rootNode;
		BSTNode<K, T> parent  = null;
		while(current != null) {
			if (current.getkey().compareTo(key) == 0) {
				break;
			}
			if (current.getkey().compareTo(key) > 0) {
				parent  = current;
				current = current.getLeft();
			} else {
				parent  = current;
				current = current.getRight();
			}
		}
		return new ParentNodePair(current, parent);
	}
	
	
	private boolean isRootNode(BSTNode<K, T> node) {
		if (node.getkey().compareTo(rootNode.getkey()) == 0) {
			return true;
		}
		return false;
	}
	
	private void swapNodeData(BSTNode<K, T> node1, BSTNode<K, T> node2) {
		K key1  = node1.getkey();
		T data1 = node1.getData();
		node1.setProperties(node2.getkey(), node2.getData());
		node2.setProperties(key1, data1);
	}
}
