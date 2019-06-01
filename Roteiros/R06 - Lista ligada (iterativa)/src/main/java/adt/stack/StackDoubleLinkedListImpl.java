package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	private boolean inputIsValid(T element) {
		boolean validInput = true;

		if (element == null) {
			validInput = false;
		}

		return validInput;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else if (this.inputIsValid(element)) {
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}

		T removed = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
		this.top.removeLast();
		return removed;
	}

	@Override
	public T top() {
		T top = null;

		if (!this.isEmpty()) {
			top = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
		}

		return top;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}
}
