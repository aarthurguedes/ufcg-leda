package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected boolean inputIsValid(T element) {
		return (element != null);
	}

	protected int height (BSTNode<T> node) {
		int height = -1;

		if (!node.isEmpty()) {
			int leftHeight = 1 + height((BSTNode<T>) node.getLeft());
			int rightHeight = 1 + height((BSTNode<T>) node.getRight());

			height = Math.max(leftHeight, rightHeight);
		}

		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> resultSearch = new BSTNode<>();

		if (inputIsValid(element) && !node.isEmpty()) {

			if (element.equals(node.getData())) {
				resultSearch = node;
			} else if (element.compareTo(node.getData()) < 0) {
				resultSearch = search(element, (BSTNode<T>) node.getLeft());
			} else {
				resultSearch = search(element, (BSTNode<T>) node.getRight());
			}
		}

		return resultSearch;
	}

	@Override
	public void insert(T element) {
		insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (inputIsValid(element)) {

			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<>());
				node.setRight(new BSTNode<>());

				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else if (element.compareTo(node.getData()) < 0) {
				insert(element, (BSTNode<T>) node.getLeft());
			} else {
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> maximum = null;

		if (!node.isEmpty() && node.getRight().isEmpty()) {
			maximum = node;
		} else if (!node.isEmpty()) {
			maximum = maximum((BSTNode<T>) node.getRight());
		}

		return maximum;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> minimum = null;

		if (!node.isEmpty() && node.getLeft().isEmpty()) {
			minimum = node;
		} else if (!node.isEmpty()) {
			minimum = minimum((BSTNode<T>) node.getLeft());
		}

		return minimum;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		return sucessor(element, search(element));
	}

	private BSTNode<T> sucessor(T element, BSTNode<T> node) {
		BSTNode<T> sucessor = null;

		if (inputIsValid(element) && !node.isEmpty() && !node.equals(maximum())) {

			if (!node.getRight().isEmpty()) {
				sucessor = minimum((BSTNode<T>) node.getRight());
			} else {
				sucessor = sucessor(node, (BSTNode<T>) node.getParent());
			}
		}

		return sucessor;
	}

	private BSTNode<T> sucessor(BSTNode<T> node, BSTNode<T> currentParent) {
		BSTNode<T> sucessor;

		if (!currentParent.isEmpty() && currentParent.getData().compareTo(node.getData()) > 0) {
			sucessor = currentParent;
		} else {
			sucessor = sucessor(node, (BSTNode<T>) currentParent.getParent());
		}

		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		return predecessor(element, search(element));
	}

	private BSTNode<T> predecessor(T element, BSTNode<T> node) {
		BSTNode<T> predecessor = null;

		if (inputIsValid(element) && !node.isEmpty() && !node.equals(minimum())) {

			if (!node.getLeft().isEmpty()) {
				predecessor = maximum((BSTNode<T>) node.getLeft());
			} else {
				predecessor = predecessor(node, (BSTNode<T>) node.getParent());
			}
		}

		return predecessor;
	}

	private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> currentParent) {
		BSTNode<T> predecessor;

		if (!currentParent.isEmpty() && currentParent.getData().compareTo(node.getData()) < 0) {
			predecessor = currentParent;
		} else {
			predecessor = predecessor(node, (BSTNode<T>) currentParent.getParent());
		}

		return predecessor;
	}

	@Override
	public void remove(T element) {
		if (inputIsValid(element)) {
			remove(search(element));
		}
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {

			if (node.isLeaf()) {		// node.left and node.right -> NIL
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {		// node has one child

				if (!node.equals(this.root)) {

					if (node.equals(node.getParent().getLeft())) {		// node is left child

						if (!(node.getLeft().isEmpty())) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {		// node is right child

						if (!(node.getLeft().isEmpty())) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {	// node = root

					if (!(node.getLeft().isEmpty())) {
						this.root = (BSTNode<T>) node.getLeft();
					} else {
						this.root = (BSTNode<T>) node.getRight();
					}
				}
			} else {		// node has two children
				BSTNode<T> sucessor = sucessor(node.getData());
				T dataSucessor = sucessor.getData();

				remove(sucessor);

				node.setData(dataSucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);

		return array;
	}

	private int preOrder(BSTNode node, T[] array, int i) {
		if (!node.isEmpty()) {
			array[i] = (T) node.getData();
			i++;

			i = preOrder((BSTNode) node.getLeft(), array, i);
			i = preOrder((BSTNode) node.getRight(), array, i);
		}

		return i;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);

		return array;
	}

	private int order(BSTNode node, T[] array, int i) {
		if (!node.isEmpty()) {
			i = order((BSTNode) node.getLeft(), array, i);

			array[i] = (T) node.getData();
			i++;

			i = order((BSTNode) node.getRight(), array, i);
		}

		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);

		return array;
	}

	private int postOrder(BSTNode node, T[] array, int i) {
		if (!node.isEmpty()) {
			i = postOrder((BSTNode) node.getLeft(), array, i);
			i = postOrder((BSTNode) node.getRight(), array, i);

			array[i] = (T) node.getData();
			i++;
		}

		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
