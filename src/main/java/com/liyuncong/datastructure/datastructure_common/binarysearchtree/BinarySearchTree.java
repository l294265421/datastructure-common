package com.liyuncong.datastructure.datastructure_common.binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;

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
		while (cursor != root && cursor.getRight() != null) {
			cursor = root.getRight();
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

	}
}
