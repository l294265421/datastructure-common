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
	 * 获得这颗红黑树中关键字最小的节点
	 * @return
	 */
	public TreeNode<T> treeMinimum() {
		return innerTreeMinimum(root);
	}
	
	private TreeNode<T> innerTreeMinimum(TreeNode<T> target) {
		if (target == null || target == TreeNode.SENTINEL) {
			return null;
		}
		
		TreeNode<T> cursor = target;
		while (cursor.getLeft() != TreeNode.SENTINEL) {
			cursor = cursor.getLeft();
		}
		return cursor;
	}
	
	/**
	 * 获得target节点在树中的后继（中根遍历）
	 * @param target
	 * @return
	 */
	public TreeNode<T> treeSuccessor(TreeNode<T> target) {
		if (target == null || target == TreeNode.SENTINEL) {
			return null;
		}
		
		if (target.getRight() != TreeNode.SENTINEL) {
			return innerTreeMinimum(target.getRight());
		}
		
		TreeNode<T> cursor = target.getParent();
		while (cursor != TreeNode.SENTINEL && target == cursor.getRight()) {
			target = cursor;
			cursor = cursor.getParent();
		}
		return cursor;
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
	
	public void rbDelete(TreeNode<T> target) {
		if (target == null || target == TreeNode.SENTINEL) {
			return;
		}
		
		// 确定要删除的元素
		TreeNode<T> realDeleteNode = null;
		if (target.getLeft() == TreeNode.SENTINEL || 
				target.getRight() == TreeNode.SENTINEL) {
			realDeleteNode = target;
		} else {
			realDeleteNode = treeSuccessor(target);
		}
		
		// 确定待删除元素的非null孩子
		TreeNode<T> notNullChildOfRealDeleteNode = null;
		if (realDeleteNode.getLeft() != TreeNode.SENTINEL) {
			notNullChildOfRealDeleteNode = realDeleteNode.getLeft();
		} else {
			notNullChildOfRealDeleteNode = realDeleteNode.getRight();
		}
		notNullChildOfRealDeleteNode.setParent(realDeleteNode.getParent());
		
		if (realDeleteNode.getParent() == TreeNode.SENTINEL) {
			root = notNullChildOfRealDeleteNode;
		} else if(realDeleteNode == realDeleteNode.getParent().getLeft()){
			realDeleteNode.getParent().setLeft(notNullChildOfRealDeleteNode);
		} else {
			realDeleteNode.getParent().setRight(notNullChildOfRealDeleteNode);
		}
		
		if (realDeleteNode != target) {
			target.setKey(realDeleteNode.getKey());
		}
		
		if (realDeleteNode.getColor() == Color.BLACK) {
			rbDeleteFixup(notNullChildOfRealDeleteNode);
		}
	}
	
	public void rbDeleteFixup(TreeNode<T> target) {
		while (target != root && target.getColor() == Color.BLACK) {
			if (target == target.getParent().getLeft()) {
				TreeNode<T> brother = target.getParent().getRight();
				
				// 情况一
				if (brother.getColor() == Color.RED) {
					brother.setColor(Color.BLACK);
					target.getParent().setColor(Color.RED);
					leftRotate(target.getParent());
					brother = target.getParent().getRight();
				}
				// 情况二
				if (brother.getLeft().getColor() == Color.BLACK && 
						brother.getRight().getColor() == Color.RED) {
					brother.setColor(Color.RED);
					target = target.getParent();
				} else {
					// 情况三
					if (brother.getParent().getRight().getColor() == 
							Color.BLACK) {
						brother.getLeft().setColor(Color.BLACK);
						brother.setColor(Color.RED);
						rightRotate(brother);
						brother = target.getParent().getRight();
					}
					// 情况四
					brother.setColor(target.getParent().getColor());
					target.getParent().setColor(Color.BLACK);
					brother.getRight().setColor(Color.BLACK);
					leftRotate(target.getParent());
					target = root;
				}
				
			} else {
				TreeNode<T> brother = target.getParent().getLeft();
				
				// 情况一
				if (brother.getColor() == Color.RED) {
					brother.setColor(Color.BLACK);
					target.getParent().setColor(Color.RED);
					rightRotate(target.getParent());
					brother = target.getParent().getLeft();
				}
				// 情况二
				if (brother.getRight().getColor() == Color.BLACK && 
						brother.getLeft().getColor() == Color.RED) {
					brother.setColor(Color.RED);
					target = target.getParent();
				} else {
					// 情况三
					if (brother.getParent().getLeft().getColor() == 
							Color.BLACK) {
						brother.getRight().setColor(Color.BLACK);
						brother.setColor(Color.RED);
						leftRotate(brother);
						brother = target.getParent().getLeft();
					}
					// 情况四
					brother.setColor(target.getParent().getColor());
					target.getParent().setColor(Color.BLACK);
					brother.getLeft().setColor(Color.BLACK);
					rightRotate(target.getParent());
					target = root;
				}
			}
		}
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
