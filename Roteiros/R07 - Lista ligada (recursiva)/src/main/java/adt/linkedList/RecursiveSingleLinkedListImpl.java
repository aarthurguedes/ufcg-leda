package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

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
		return this.getData() == null;
	}

	@Override
	public int size() {
		int size = 0;

		if (!this.isEmpty()) {
			size = 1 + this.getNext().size();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (!this.isEmpty() && this.inputIsValid(element)) {
			if (this.getData().equals(element)) {
				result = this.getData();
			} else {
				result = this.getNext().search(element);
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (this.inputIsValid(element)) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.inputIsValid(element) && !this.isEmpty()) {
			if (this.getData().equals(element) && !this.getNext().isEmpty()) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else if (this.getData().equals(element) && this.getNext().isEmpty()){
				this.setData(null);
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];

		if (!this.isEmpty()) {
			toArray(result, 0, this.size());
		}

		return result;
	}

	private void toArray(T[] array, int currentSize, int size) {
		if (!this.isEmpty() && this.getNext().isEmpty()) {
			array[currentSize] = this.getData();
		} else if (!this.isEmpty() && !this.getNext().isEmpty()) {
			array[currentSize] = this.getData();
			currentSize++;
			this.getNext().toArray(array, currentSize, size);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
