package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T top = null;

		if (!this.isEmpty()) {
			top = this.array[this.top];
		}

		return top;
	}

	@Override
	public boolean isEmpty() {
		boolean arrayEmpty = false;

		if (this.top == -1) {
			arrayEmpty = true;
		}

		return arrayEmpty;
	}

	@Override
	public boolean isFull() {
		boolean arrayFull = false;

		if (this.top == this.array.length - 1) {
			arrayFull = true;
		}

		return arrayFull;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else if (element != null) {
			this.top++;
			this.array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}

		this.top--;
		return this.array[this.top + 1];
	}
}
