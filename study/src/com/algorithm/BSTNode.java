package com.algorithm;

/**
 * Binary search tree. Internal representation
 * 
 * Like any other dynamic data structure, BST requires storing of some
 * additional auxiliary data, in order to keep its structure. Each node of
 * binary tree contains the following information:
 * 
 * a value (user's data); a link to the left child (auxiliary data); a link to
 * the right child (auxiliary data). Depending on the size of user data, memory
 * overhead may vary, but in general it is quite reasonable. In some
 * implementations, node may store a link to the parent, but it depends on
 * algorithm, programmer want to apply to BST. For basic operations, like
 * addition, removal and search a link to the parent is not necessary. It is
 * needed in order to implement iterator
 * 
 * @author arisupply
 *
 */
public class BSTNode {
	private int value;
	private BSTNode left;
	private BSTNode right;

	public BSTNode(int value) {
		this.value = value;
		left = null;
		right = null;
	}
}