package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	protected boolean inputIsValid(BSTNode<T> node) {
		return (node != null && !node.isEmpty());
	}

	@Override
	public void insert(T element) {
		if (inputIsValid(element)) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp(node);
		}
	}

	@Override
	public void remove(T element) {
		if (inputIsValid(element)) {
			BSTNode<T> parentNode = ((BSTNode<T>) super.search(element).getParent());
			super.remove(element);

			if (parentNode == null) {
				rebalanceUp(root);
			} else {
				rebalanceUp(parentNode);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;

		if (inputIsValid(node)) {
			result = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}

		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (inputIsValid(node)) {
			BSTNode<T> newRoot = null;
			int balance = calculateBalance(node);

			if (balance > 1) {

				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
					Util.leftRotation((BSTNode<T>) node.getLeft());
				}

				newRoot = Util.rightRotation(node);
			} else if (balance < -1) {

				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					Util.rightRotation((BSTNode<T>) node.getRight());

				}

				newRoot = Util.leftRotation(node);
			}

			if (newRoot != null && newRoot.getParent() == null) {
				this.root = newRoot;
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (inputIsValid(node)) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
}
