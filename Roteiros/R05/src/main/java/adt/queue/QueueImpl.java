package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T head = null;

		if (!this.isEmpty()) {
			head = this.array[0];
		}

		return head;
	}

	@Override
	public boolean isEmpty() {
		boolean arrayEmpty = false;

		if (this.tail == -1) {
			arrayEmpty = true;
		}

		return arrayEmpty;
	}

	@Override
	public boolean isFull() {
		boolean arrayFull = false;

		if (this.tail == this.array.length - 1) {
			arrayFull = true;
		}

		return arrayFull;
	}

	private void shiftLeft() {
		for (int i = 0; i < this.tail; i++) {
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		} else if (element != null) {
			this.tail++;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T removed = this.array[0];
		this.shiftLeft();
		this.tail--;

		return removed;
	}
}
