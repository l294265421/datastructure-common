package com.liyuncong.datastructure.datastructure_common;

/**
 * 二叉查找树
 * @author yuncong
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;
	
	public BinarySearchTree() {
	}

	public BinarySearchTree(T root) {
		super();
		this.root = new TreeNode<T>(root);
	}
	
	/**
	 * 中根遍历该二叉查找树
	 */
	public void inorderTreeWalk() {
		innerInorderTreeWalk(root);
	}
	
	private void innerInorderTreeWalk(TreeNode<T> root) {
		if (root != null) {
			innerInorderTreeWalk(root.left);
			System.out.println(root.getKey());
			innerInorderTreeWalk(root.right);
		}
	}
	
	/**
	 * 在这个二叉查找树中查找键为key的元素
	 * @param key
	 * @return
	 */
	public TreeNode<T> treeSearch(T key) {
		TreeNode<T> cursor = root;
		// 这个元素不为null，为这个元素不是目标元素
		while (cursor != null && !cursor.getKey().equals(key)) {
			if (cursor.getKey().compareTo(key) < 0) {
				cursor = cursor.getLeft();
			} else {
				cursor = cursor.getRight();
			}
		}
		return cursor;
	}
	
	/**
	 * 获得这颗二叉树中关键字最小的节点
	 * @return
	 */
	public TreeNode<T> treeMinimum() {
		return innerTreeMinimum(root);
	}
	
	private TreeNode<T> innerTreeMinimum(TreeNode<T> target) {
		TreeNode<T> cursor = target;
		while (cursor != null && cursor.getLeft() != null) {
			cursor = cursor.getLeft();
		}
		return cursor;
	}
	
	/**
	 * 返回这颗二叉树中关键字最大的节点
	 * @return
	 */
	public TreeNode<T> treeMaximum() {
		return innerTreeMaximum(root);
	}
	
	private TreeNode<T> innerTreeMaximum(TreeNode<T> target) {
		TreeNode<T> cursor = target;
		while (cursor != null && cursor.getRight() != null) {
			cursor = cursor.getRight();
		}
		return cursor;
	}
	
	/**
	 * 获得target节点在树中的后继（中根遍历）
	 * @param target
	 * @return
	 */
	public TreeNode<T> treeSuccessor(TreeNode<T> target) {
		if (target == null) {
			return null;
		}
		
		if (target.getRight() != null) {
			return innerTreeMaximum(target.getRight());
		}
		
		TreeNode<T> cursor = target.getParent();
		while (cursor != null && target == cursor.getRight()) {
			target = cursor;
			cursor = cursor.getParent();
		}
		return cursor;
	}
	
	/**
	 * 向二叉查找树中插入节点
	 * @param node
	 */
	public void treeInsert(TreeNode<T> node) {
		TreeNode<T> candidateParent = null;
		TreeNode<T> cursor = root;
		while (cursor != null) {
			candidateParent = cursor;
			if (node.getKey().compareTo(cursor.getKey()) < 0) {
				cursor = cursor.getLeft();
			} else {
				cursor = cursor.getRight();
			}
		}
		node.setParent(candidateParent);
		if (candidateParent == null) {
			root = node;
		} else if(node.getKey().compareTo(candidateParent.getKey()) < 0){
			candidateParent.setLeft(node);
		} else {
			candidateParent.setRight(node);
		}
	}
	
	/**
	 * 从二叉查找树中删除节点
	 * @param node
	 */
	public void treeDelete(TreeNode<T> node) {
		if (node == null) {
			return;
		}
		
		TreeNode<T> realDeleteNode = null;
		// 确定待删除元素
		if (node.getLeft() == null || node.getRight() == null) {
			realDeleteNode = node;
		} else {
			realDeleteNode = treeSuccessor(node);
		}
		
		// 确定待删除元素的非null孩子
		TreeNode<T> notNullChildOfRealDeleteNode = null;
		if (realDeleteNode.getLeft() != null) {
			notNullChildOfRealDeleteNode = realDeleteNode.getLeft();
		} else {
			notNullChildOfRealDeleteNode = realDeleteNode.getRight();
		}
		
		if (notNullChildOfRealDeleteNode != null) {
			notNullChildOfRealDeleteNode.setParent(realDeleteNode.getParent());
		}
		
		if (realDeleteNode.getParent() == null) {
			root = notNullChildOfRealDeleteNode;
		} else if (realDeleteNode == realDeleteNode.getParent().getLeft()) {
			realDeleteNode.getParent().setLeft(notNullChildOfRealDeleteNode);
		} else {
			realDeleteNode.getParent().setRight(notNullChildOfRealDeleteNode);
		}
		
		if (realDeleteNode != node) {
			node.setKey(realDeleteNode.getKey());
		}
	}

	/**
	 * 二叉查找树节点
	 * @author yuncong
	 *
	 * @param <T>
	 */
	public static class TreeNode<T extends Comparable<T>> {
		private T key;
		private TreeNode<T> parent;
		private TreeNode<T> left;
		private TreeNode<T> right;

		public TreeNode(T key) {
			super();
			this.key = key;
		}

		public T getKey() {
			return key;
		}

		public void setKey(T key) {
			this.key = key;
		}

		public TreeNode<T> getParent() {
			return parent;
		}

		public void setParent(TreeNode<T> parent) {
			this.parent = parent;
		}

		public TreeNode<T> getLeft() {
			return left;
		}

		public void setLeft(TreeNode<T> left) {
			this.left = left;
		}

		public TreeNode<T> getRight() {
			return right;
		}

		public void setRight(TreeNode<T> right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return "TreeNode [key=" + key + ", parent=" + parent + ", left="
					+ left + ", right=" + right + "]";
		}

	}
	
}
