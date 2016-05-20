package com.liyuncong.datastructure.datastructure_common;

import static org.junit.Assert.*;

import org.junit.Test;

import com.liyuncong.datastructure.datastructure_common.BinarySearchTree.TreeNode;

public class BinarySearchTreeTest {

	@Test
	public void test() {
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		TreeNode<Integer> treeNode1 = new TreeNode<Integer>(4);
		TreeNode<Integer> treeNode2 = new TreeNode<Integer>(3);
		TreeNode<Integer> treeNode3 = new TreeNode<Integer>(5);
		binarySearchTree.treeInsert(treeNode1);
		binarySearchTree.treeInsert(treeNode2);
		binarySearchTree.treeInsert(treeNode3);
		binarySearchTree.inorderTreeWalk();
		System.out.println("--------------");
		System.out.println(binarySearchTree.treeMinimum().getKey());
		System.out.println("--------------");
		System.out.println(binarySearchTree.treeMaximum().getKey());
		System.out.println("--------------");
		binarySearchTree.treeDelete(treeNode1);
		binarySearchTree.inorderTreeWalk();
	}

}
