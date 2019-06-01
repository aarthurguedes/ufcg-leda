package adt.bst.extended;

import java.util.*;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if (comparator.compare(element, node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight());
		} else {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public T[] sort(T[] array) {
		if (array != null) {
			this.root = new BSTNode<>();

			for (T element: array) {
				insert(element);
			}

			array = order();
		}

		return array;
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(this.root, array, 0);

		return array;
	}

	private int reverseOrder(BSTNode node, T[] array, int i) {
		if (!node.isEmpty()) {
			i = reverseOrder((BSTNode) node.getRight(), array, i);

			array[i] = (T) node.getData();
			i++;

			i = reverseOrder((BSTNode) node.getLeft(), array, i);
		}

		return i;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
