package com.liyuncong.datastructure.datastructure_common;


public class RBTree<T extends Comparable<T>> {
	private TreeNode<T> root;
	
	public RBTree() {
	}
	
	public RBTree(TreeNode<T> root) {
		super();
		this.root = root;
	}

	/**
	 * 左旋
	 * @param node
	 */
	public void leftRotate(TreeNode<T> node) {
		if (node == null || node.getRight() == TreeNode.SENTINEL) {
			return;
		}
		
		TreeNode<T> newChildrenRoot = node.getRight();
		
		node.setRight(newChildrenRoot.getLeft());
		if (newChildrenRoot.getLeft() != TreeNode.SENTINEL) {
			newChildrenRoot.getLeft().setParent(node);
		}
		
		newChildrenRoot.setParent(node.getParent());
		if (node.getParent() == TreeNode.SENTINEL) {
			root = newChildrenRoot;
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(newChildrenRoot);
		} else {
			node.getParent().setRight(newChildrenRoot);
		}
		
		newChildrenRoot.setLeft(node);
		node.setParent(newChildrenRoot);
	}
	
	/**
	 * 右旋
	 * @param node
	 */
	private void rightRotate(TreeNode<T> node) {
		if (node == null || node.getLeft() == TreeNode.SENTINEL) {
			return;
		}
		
		TreeNode<T> newChildrenRoot = node.getLeft();
		
		node.setLeft(newChildrenRoot.getRight());
		if (newChildrenRoot.getRight() != TreeNode.SENTINEL) {
			newChildrenRoot.getRight().setParent(node);
		}
		
		newChildrenRoot.setParent(node.getParent());
		if (node.getParent() == TreeNode.SENTINEL) {
			root = newChildrenRoot;
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(newChildrenRoot);
		} else {
			node.getParent().setRight(newChildrenRoot);
		}
		
		newChildrenRoot.setRight(node);
		node.setParent(newChildrenRoot);
	}

	public void rbInsert(TreeNode<T> node) {
		if (node == null) {
			return;
		}
		
		TreeNode<T> candidateParent = TreeNode.SENTINEL;
		TreeNode<T> cursor = root;
		while (cursor != null && cursor != TreeNode.SENTINEL) {
			if (node.getKey().compareTo(cursor.getKey()) < 0) {
				cursor = cursor.getLeft();
			} else {
				cursor = cursor.getRight();
			}
		}
		
		node.setParent(candidateParent);
		if (candidateParent == TreeNode.SENTINEL) {
			root = node;
		} else if (node.getKey().compareTo(candidateParent.getKey()) < 0) {
			candidateParent.setLeft(node);
		} else {
			candidateParent.setRight(node);
		}
		
		node.setLeft(TreeNode.SENTINEL);
		node.setRight(TreeNode.SENTINEL);
		node.setColor(Color.RED);
		
		rbInsertFixup(node);
	}
	
	private void rbInsertFixup(TreeNode<T> node) {
		while (node.getParent().getColor() == Color.RED) {
			if (node.getParent() == node.getParent().getParent().getLeft()) {
				TreeNode<T> uncle = node.getParent().getParent().getRight();
				// 情况一
				if (uncle.color == Color.RED) {
					uncle.setColor(Color.BLACK);
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					node = node.getParent().getParent();
				} else {
					// 情况二
					if (node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(node);
					}
					// 情况三
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					rightRotate(node.getParent().getParent());
				}
			} else {
				TreeNode<T> uncle = node.getParent().getParent().getLeft();
				// 情况一
				if (uncle.color == Color.RED) {
					uncle.setColor(Color.BLACK);
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					node = node.getParent().getParent();
				} else {
					// 情况二
					if (node == node.getParent().getLeft()) {
						node = node.getParent();
						rightRotate(node);
					}
					// 情况三
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					leftRotate(node.getParent().getParent());
				}
			}
		}
		root.setColor(Color.BLACK);
	}
	
	/**
	 * 红黑树节点
	 * @author yuncong
	 *
	 * @param <T>
	 */
	public static class TreeNode<T extends Comparable<T>> {
		private T key;
		private TreeNode<T> parent = SENTINEL;
		private TreeNode<T> left = SENTINEL;
		private TreeNode<T> right = SENTINEL;
		private Color color = Color.RED;
		
		public final static TreeNode SENTINEL = new TreeNode();
		
		private TreeNode() {
			color = Color.BLACK;
		} 
		
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

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return "TreeNode [key=" + key + ", parent=" + parent + ", left="
					+ left + ", right=" + right + ", color=" + color
					+ ", SENTINEL=" + SENTINEL + "]";
		}
		
	}
	
	public enum Color {
		RED, BLACK;
	}
}
