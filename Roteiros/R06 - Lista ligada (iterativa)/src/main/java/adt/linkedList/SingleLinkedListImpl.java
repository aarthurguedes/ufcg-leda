package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	protected boolean inputIsValid(T element) {
		boolean validInput = true;

		if (element == null) {
			validInput = false;
		}

		return validInput;
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.getHead();

		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (inputIsValid(element)) {
			SingleLinkedListNode<T> aux = this.getHead();

			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					result = aux.getData();
				}
				aux = aux.getNext();
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (this.inputIsValid(element)) {
			SingleLinkedListNode<T> auxHead = this.getHead();

			if (this.getHead().isNIL()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.getHead());
				this.setHead(newHead);
			} else {
				while (!auxHead.getNext().isNIL()) {
					auxHead = auxHead.getNext();
				}

				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, auxHead.getNext());
				auxHead.setNext(newNode);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.inputIsValid(element)) {
			if (this.getHead().getData().equals(element)) {
				this.head = this.getHead().getNext();
			} else {
				SingleLinkedListNode<T> aux = this.getHead();
				SingleLinkedListNode<T> previous = this.getHead();

				while (!aux.isNIL() && !aux.getData().equals(element)) {
					previous = aux;
					aux = aux.getNext();
				}

				if (!aux.isNIL()) {
					previous.setNext(aux.getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = this.getHead();
		int count = 0;

		while (!aux.isNIL()) {
			result[count] = aux.getData();
			aux = aux.getNext();
			count++;
		}

		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
