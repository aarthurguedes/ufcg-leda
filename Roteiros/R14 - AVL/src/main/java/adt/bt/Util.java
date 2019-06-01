package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> oldRight = (BSTNode<T>) node.getRight();
		BSTNode<T> aux = (BSTNode<T>) oldRight.getLeft();

		oldRight.setParent(parent);
		oldRight.setLeft(node);

		node.setParent(oldRight);
		node.setRight(aux);

		aux.setParent(node);

		if (parent != null && !parent.isEmpty()) {

			if (node.equals(parent.getLeft())) {
				parent.setLeft(oldRight);
			} else {
				parent.setRight(oldRight);
			}
		}

		return oldRight;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> oldLeft = (BSTNode<T>) node.getLeft();
		BSTNode<T> aux = (BSTNode<T>) oldLeft.getRight();

		oldLeft.setParent(parent);
		oldLeft.setRight(node);

		node.setParent(oldLeft);
		node.setLeft(aux);

		aux.setParent(node);

		if (parent != null && !parent.isEmpty()) {

			if (node.equals(parent.getLeft())) {
				parent.setLeft(oldLeft);
			} else {
				parent.setRight(oldLeft);
			}
		}

		return oldLeft;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
